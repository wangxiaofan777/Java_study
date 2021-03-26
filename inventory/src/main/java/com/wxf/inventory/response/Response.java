package com.wxf.inventory.response;

/**
 * 请求的响应
 */

public class Response {

    public static final String SUCCESS = "success";
    public static final String FAILURE = "fail";

    public Response(String status) {
        this.status = status;
    }

    private String message;
    private String status;

    public Response(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
