package com.redltd.tech.db;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DataSource {

    @Value("${data.source.host}")
    private String Host;

    @Value("${data.source.name}")
    private String Db_Name;

    @Value("${data.source.username}")
    private String Username;

    @Value("${data.source.password}")
    private String Password;

    @Value("${data.source.port}")
    private String Port;

    @Bean
    @Primary
    public HikariDataSource hikariDataSource(){
        String getUrl = "jdbc:sqlserver://"+Host+":"+Port+";databaseName="+Db_Name;
        return DataSourceBuilder
                .create()
                .username(Username)
                .password(Password)
                .url(getUrl)
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(HikariDataSource hikariDataSource) {
        return new JdbcTemplate(hikariDataSource);
    }


}
