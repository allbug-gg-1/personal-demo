package com.test.common.code;

public enum DatabaseMode {

    JPA(0, "jpa"),
    MyBatis(1, "myBatis"),
    MyBatisPlus(2, "myBatisPlus");

    DatabaseMode(int code, String text) {
        this.code = code;
        this.text = text;
    }

    private int code;

    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
