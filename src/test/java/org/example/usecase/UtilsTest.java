package org.example.usecase;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UtilsTest {

    @Test
    void phone_is_not_allowed() {
        ValidateException thrown = assertThrows(
                ValidateException.class,
                () -> {
                    RuleEntity ruleEntity = mock(RuleEntity.class);
                    when(ruleEntity.getIssueWay()).thenReturn(Long.valueOf(6));

                    Utils.verifyBeforeAcceptanceForPhoneAndMobile(ruleEntity);
                });
        assertEquals("电话与手机平台不允许此操作!", thrown.getMessage());
    }
}