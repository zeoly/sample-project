package com.yahacode.sample.multitenancy.config;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.EmptyInterceptor;

import java.util.List;

public class MultitenancyInterceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql) {
        if (StringUtils.isNotBlank(TenantContext.get())) {
            String tenant = TenantContext.get();
            List<String> tables = List.of(TenantProperties.getStr().split(","));
            for (String table : tables) {
                if (sql.contains(table)) {
                    sql = sql.replace(table, tenant + "_" + table);
                }
            }
        }
        return super.onPrepareStatement(sql);
    }
}
