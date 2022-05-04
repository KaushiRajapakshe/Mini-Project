package com.shopping.mini.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by @author Kaushalya Rajapakshe on 2022-04-07
 */
@Configuration
public class DataSourceConfig {
    @Bean(name="mini")
    @ConfigurationProperties(prefix="db.mini")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("mini") DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
