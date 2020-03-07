package org.example.usecase;

import org.apache.commons.lang.StringUtils;

public class BackToEntryBeforeAcceptance {
    public static final int MOBILE = 11;
    private BackToEntryBeforeAcceptanceRepository repository;

    private UserService userService;
    private UseCaseCallback useCaseCallback;

    public BackToEntryBeforeAcceptance(UseCaseCallback<BackToEntryBeforeAcceptanceResponseModel> useCaseCallback, BackToEntryBeforeAcceptanceRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public void execute(BackToEntryBeforeAcceptanceRequestModel request) throws Exception, ValidateException {
        UserTransaction tran = null;
        try {
            String sendCode = request.getSendCode();
            String operatorId = request.getOperatorId();
            String backInfo = request.getBackInfo();

            tran = TransactionProvider.get();
            tran.begin();

            if (StringUtils.isNotBlank(sendCode)) {
                RuleEntity ruleEntity = repository.loadRuleBySendCode(sendCode);
                new Utils().validateBeforeAcceptance(sendCode, operatorId, ruleEntity, this);

                Boolean elaboratelySelected = repository.isElaboratelySelected(ruleEntity.getRuleId());
                if (elaboratelySelected) {
                    repository.updateUWStatusToUnderwrite(ruleEntity.getRuleId());
                } else {
                    repository.cancelUWApplication(ruleEntity.getRuleId());
                }

                Boolean isPreferentialTax = repository.isPreferentialTax(ruleEntity.getRuleId(), ruleEntity.getSendCode());

                Long oldRuleState = ruleEntity.getPStateId();
                ruleEntity.updateRuleStatus(ruleEntity, request, elaboratelySelected, isPreferentialTax);
                repository.update(ruleEntity);

                RuleStateChangeEntity ruleStateChangeEntity = new RuleStateChangeEntity();
                ruleStateChangeEntity.ctreateRecodChangeAcceptanceToInput(ruleEntity, oldRuleState);

                repository.crate(ruleEntity, oldRuleState, operatorId, backInfo);
                repository.updateExemptAddFee(ruleEntity.getRuleId());

                RuleExtEntity ruleExtEntity = repository.loadRuleExtEntityByRuleId(ruleEntity.getRuleId());
                if (ruleExtEntity != null && ruleExtEntity.getTempsaveId() != null) {
                    ruleExtEntity.releaseSecondReportTS();
                    repository.updateRuleExtEntity(ruleExtEntity);
                }
            } else {
                String billNo = request.getBillNo();

                if (!repository.billNoIsExists(billNo)) {
                    throw new ValidateException("单据号码不存在！");
                }
                repository.billNoBackToEntry(repository.loadBillIdByBillNo(billNo), backInfo, Long.valueOf(operatorId));
            }

            tran.commit();
            BackToEntryBeforeAcceptanceResponseModel responseModel = new BackToEntryBeforeAcceptanceResponseModel();
            TransResult transResult = new TransResult();
            transResult.setResultCode(TransResultCodeEnum.SUCCESS.getValue());
            transResult.setResultInfo("成功！");
            responseModel.transResult = transResult;
            useCaseCallback.onSuccess(responseModel);

        } catch (ValidateException e) {
            if (tran != null) {
                try {
                    tran.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            throw new ValidateException(e.getMessage());
        } catch (Exception e) {
            if (tran != null) {
                try {
                    tran.rollback();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                SQLValidateInfoFormat(e);
                throw new RuntimeException("退回录入失败", e);
            }
        }
    }

    private void SQLValidateInfoFormat(Exception e) {

    }
}
