package com.nhnacademy.family_relationship_certification.config;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class DatabaseConfig {

    private final DatabaseProperties properties;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setMaxTotal(properties.getMaxTotal());
        dataSource.setMaxIdle(properties.getMaxIdle());
        dataSource.setMinIdle(properties.getMinIdle());
        dataSource.setTestOnBorrow(properties.isTestOnBorrow());
        if(properties.isTestOnBorrow()) {
            dataSource.setValidationQuery(dataSource.getValidationQuery());
        }
        Resource resource = new ClassPathResource("script/schema.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(resource);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
