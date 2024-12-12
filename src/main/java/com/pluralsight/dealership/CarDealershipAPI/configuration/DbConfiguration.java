package com.pluralsight.dealership.CarDealershipAPI.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;

@Configuration
public class DbConfiguration {
    private final BasicDataSource basicDataSource;


    @Bean
    public DataSource dataSource(){
        return basicDataSource;
    }

    public DbConfiguration(
            @Value("${datasource.url}") String url,
            @Value("${datasource.username}") String username,
            @Value("${datasource.password}") String password
    )
    {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

    }

}