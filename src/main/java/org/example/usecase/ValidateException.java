package org.example.usecase;

public class ValidateException extends Exception {
    private String errorCode;

    public ValidateException(String s) {
        super(s);
    }

    public ValidateException(Throwable e) {
        super(e);
    }

    public ValidateException(String errorCode, String errorDesc) {
        super(errorDesc);
        this.errorCode = errorCode;
    }

    public ValidateException(String errorCode, Throwable e) {
        super(e);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
