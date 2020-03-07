package org.example.usecase;

public enum RuleStatusEnum {

    WAITING_ACCEPTANCE( 14);

    int value;
    RuleStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}