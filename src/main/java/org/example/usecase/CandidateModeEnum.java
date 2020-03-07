package org.example.usecase;

public enum CandidateModeEnum {
    PHONE(6);
    int value;
    CandidateModeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
