package org.example.usecase;

public enum TransResultCodeEnum {
    SUCCESS("1"),
    ERROR("2"),
    FAIL("3");
    String value;

    private TransResultCodeEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
