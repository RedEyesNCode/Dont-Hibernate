package com.redeyesncode.write.models;

public class CustomStatusCodeModel extends StatusCodeModel{


    private Object data;

    public CustomStatusCodeModel(String status, int code, String message, Object data) {
        super(status, code, message);
        this.data = data;
    }

    public CustomStatusCodeModel(String status, int code, Object data) {
        super(status, code);
        this.data = data;
    }

    public CustomStatusCodeModel(String status, int code, String message) {
        super(status, code, message);
    }

    public CustomStatusCodeModel(String status, int code) {
        super(status, code);
    }
}
