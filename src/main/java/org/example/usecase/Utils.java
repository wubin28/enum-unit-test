package org.example.usecase;

public class Utils {
    static void verifyBeforeAcceptanceForPhoneAndMobile(RuleEntity ruleEntity) throws ValidateException {
        if (ruleEntity.getIssueWay() == 6 || ruleEntity.getIssueWay() == 11) {
            throw new ValidateException("电话与手机平台不允许此操作!");
        }
    }
}
