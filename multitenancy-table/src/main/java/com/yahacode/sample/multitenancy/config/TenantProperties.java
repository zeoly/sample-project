package com.yahacode.sample.multitenancy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TenantProperties {

    private static String str;

    @Value("${tenant.tables}")
    public void setStr(String str) {
        this.str = str;
    }

    public static String getStr() {
        return str;
    }
}
