package com.test.handler;

public class WebResponse {
    private int status = 200;
    private String message;
    private Object body;
    private long timestamp;

    public WebResponse() {
        this.timestamp = System.currentTimeMillis();
    }

    public WebResponse(int status) {
        this.status = status;
        this.timestamp = System.currentTimeMillis();
    }

    public WebResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
