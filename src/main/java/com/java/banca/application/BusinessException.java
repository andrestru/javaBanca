package com.java.banca.application;

public class BusinessException extends RuntimeException {
    private final String code;

    public BusinessException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
