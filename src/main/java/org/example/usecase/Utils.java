package org.example.usecase;

public class Utils {

    public static final int MOBILE = 11;

    static void verifyBeforeAcceptanceForPhoneAndMobile(RuleEntity ruleEntity) throws ValidateException {
        if (Long.valueOf(CandidateModeEnum.PHONE.getValue()).equals(ruleEntity.getIssueWay()) || ruleEntity.getIssueWay() == MOBILE) {
            throw new ValidateException("电话与手机平台不允许此操作!");
        }
    }
}
