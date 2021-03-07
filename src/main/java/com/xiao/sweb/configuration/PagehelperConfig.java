package com.xiao.sweb.configuration;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class PagehelperConfig {

    @Bean
    public PageHelper gerPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("heplerDialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArgument", "true");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
