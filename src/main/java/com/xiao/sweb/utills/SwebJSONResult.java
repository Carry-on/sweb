package com.xiao.sweb.utills;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SwebJSONResult {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer status;

    private String msg;

    private Object data;

    private String ok;

    public SwebJSONResult() {

    }

    public SwebJSONResult(String msg) {
        this.msg = msg;
    }

    public SwebJSONResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public SwebJSONResult(Integer status, String meg, Object data) {
        this.status = status;
        this.msg = meg;
        this.data = data;
    }

    public static SwebJSONResult errorData(Object data) {
        return new SwebJSONResult(501, "error", data);
    }

    public static SwebJSONResult errorTokenMsg(String msg) {
        return new SwebJSONResult(502, msg, null);
    }

    public static SwebJSONResult errorException(String msg) {
        return new SwebJSONResult(555, msg, null);
    }
}
