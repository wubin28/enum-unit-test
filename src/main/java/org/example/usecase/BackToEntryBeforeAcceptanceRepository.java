package org.example.usecase;

public class BackToEntryBeforeAcceptanceRepository {
    public RuleEntity loadRuleBySendCode(String sendCode) {
        return null;
    }

    public boolean isOnTransfer(Long policyId) {
        return false;
    }

    public boolean isTaxDeferred(Object policyId) {
        return false;
    }

    public boolean isHouseForPension(Object policyId) {
        return false;
    }

    public Integer updateRuleFeeStatus(Object policyId, String policyType, String operatorId) {
        return null;
    }

    public Boolean isElaboratelySelected(Object policyId) {
        return null;
    }

    public void updateUWStatusToUnderwrite(Object policyId) {

    }

    public void cancelUWApplication(Object policyId) {

    }

    public Boolean isPreferentialTax(Object policyId, Object sendCode) {
        return null;
    }

    public void update(RuleEntity ruleEntity) {

    }

    public void crate(RuleEntity ruleEntity, Long oldPolicyState, String operatorId, String backInfo) {

    }

    public void updateExemptAddFee(Object policyId) {

    }

    public RuleExtEntity loadRuleExtEntityByRuleId(Object policyId) {
        return null;
    }

    public void updateRuleExtEntity(RuleExtEntity ruleExtEntity) {

    }

    public boolean billNoIsExists(String billNo) {
        return false;
    }

    public boolean billNoExistsCurrentOrgan(String organId, String billNo) {
        return false;
    }

    public boolean hasStateBeforeApproval(String billNo) {
        return false;
    }

    public Object loadBillIdByBillNo(String billNo) {
        return null;
    }

    public void billNoBackToEntry(Object loadBillIdByBillNo, String backInfo, Long valueOf) {

    }
}
