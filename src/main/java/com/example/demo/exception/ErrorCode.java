package com.example.demo.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    CHARACTERS_INVALID(1002, "Syntax Error"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004,"Password must be at least 8 chacracters"),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
