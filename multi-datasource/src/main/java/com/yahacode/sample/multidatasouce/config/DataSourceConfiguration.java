package com.yahacode.sample.multidatasouce.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class DataSourceConfiguration {

    public DataSource dataSource() throws IOException {
        File[] files = Paths.get("tenants").toFile().listFiles();
        Map<Object, Object> resolvedDataSources = new HashMap<>();

        for (File file : files) {
            Properties tenantProperties = new Properties();
            tenantProperties.load(new FileInputStream(file));
            String tenantId = tenantProperties.getProperty("name");

            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
            dataSourceBuilder.url(tenantProperties.getProperty("datasource.url"));
            dataSourceBuilder.username("datasource.username");
            dataSourceBuilder.password("datasource.password");

            resolvedDataSources.put(tenantId, dataSourceBuilder.build());
        }

        AbstractRoutingDataSource dataSource = new MultiTenancyDataSource();
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get(""));
        dataSource.setTargetDataSources(resolvedDataSources);
        dataSource.afterPropertiesSet();
        return dataSource;
    }
}
