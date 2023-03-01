package com.yahacode.sample.hiddenblade.ctrl;

import java.time.LocalDateTime;

public class Test {

    String code;

    LocalDateTime time;

    public Test() {
    }

    public Test(String code, LocalDateTime time) {
        this.code = code;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
