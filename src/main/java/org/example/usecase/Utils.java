package org.example.usecase;

public class Utils {
    void validateBeforeAcceptance(String sendCode, String operatorId, RuleEntity ruleEntity, UserService userService, BackToEntryBeforeAcceptanceRepository repository) throws ValidateException {
        if (ruleEntity == null) {
            throw new ValidateException("单据不存在！");
        }
        if ("2".equals(ruleEntity.getRuleType())) {
            throw new ValidateException("当前操作暂不支持！");
        }
        if (!userService.existsUser(operatorId)) {
            throw new ValidateException("操作用户不存在！");
        }
        if (Long.valueOf(RuleStatusEnum.WAITING_ACCEPTANCE.getValue()).compareTo(ruleEntity.getPStateId()) != 0) {
            throw new ValidateException("状态必须是等待!");
        }
        if (sendCode.endsWith("026")) {
            throw new ValidateException("仅支持按号码操作，退回录入!");
        }
        if (repository.isOnTransfer(ruleEntity.getRuleId())) {
            throw new ValidateException("该单据未处理完成，请稍后执行此操作!");
        }
        if (Long.valueOf(CandidateModeEnum.PHONE.getValue()).equals(ruleEntity.getIssueWay()) || ruleEntity.getIssueWay() == BackToEntryBeforeAcceptance.MOBILE) {
            throw new ValidateException("电话与手机平台不允许此操作!");
        }
        Integer result = repository.updateRuleFeeStatus(ruleEntity.getRuleId(), ruleEntity.getRuleType(), operatorId);
        if (result == 1) {
            throw new ValidateException("该单据未处理完成，请稍后执行此操作!");
        }
        if (result == 2) {
            throw new ValidateException("有问题，不能预收!");
        }
    }
}
