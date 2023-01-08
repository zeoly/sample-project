package com.yahacode.sample.multitenancy.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
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

    @Bean
    public DataSource dataSource() throws IOException {
        File[] files = Paths.get("multitenancy/allTenants").toFile().listFiles();
        Map<Object, Object> resolvedDataSources = new HashMap<>();

        for (File file : files) {
            Properties tenantProperties = new Properties();
            tenantProperties.load(new FileInputStream(file));
            String tenantId = tenantProperties.getProperty("name");

            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
            dataSourceBuilder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
            dataSourceBuilder.url(tenantProperties.getProperty("datasource.url"));
            dataSourceBuilder.username(tenantProperties.getProperty("datasource.username"));
            dataSourceBuilder.password(tenantProperties.getProperty("datasource.password"));

            resolvedDataSources.put(tenantId, dataSourceBuilder.build());
        }

        AbstractRoutingDataSource dataSource = new MultiTenancyDataSource();
        dataSource.setDefaultTargetDataSource(resolvedDataSources.get("a"));
        dataSource.setTargetDataSources(resolvedDataSources);
        dataSource.afterPropertiesSet();
        return dataSource;
    }
}
