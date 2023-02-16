package com.yahacode.sample.multitenancy.config;

public class TenantContext {

    private static final InheritableThreadLocal<String> CURRENT = new InheritableThreadLocal<>();

    public static String get() {
        return CURRENT.get();
    }

    public static void set(String tenantId) {
        CURRENT.set(tenantId);
    }
}
