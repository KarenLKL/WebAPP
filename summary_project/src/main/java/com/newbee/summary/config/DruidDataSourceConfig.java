package com.newbee.summary.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Properties;

/**
 * Druid数据源配置
 * Created by kl09 on 2017/4/5.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = "com.newbee.summary.mybatis.dao")
public class DruidDataSourceConfig {

    @Autowired
    private DataSourceProperties properties;

    //注册dataSource
    @Bean
    @ConfigurationProperties("spring.datasource.druid")
    public DruidDataSource dataSource() {
        DruidDataSource druidDataSource = (DruidDataSource) properties.initializeDataSourceBuilder()
                .type(DruidDataSource.class).build();
        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(properties.determineUrl());
        String validationQuery = databaseDriver.getValidationQuery();
        if (validationQuery != null) {
            druidDataSource.setValidationQuery(validationQuery);
        }
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean
                .setConfigLocation(new PathMatchingResourcePatternResolver()
                        .getResource("classpath:config/mybatis/mybatis-config.xml"));
        //mybatis分页
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props); //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        /*PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:com/newbee/summary/mybatis/dao*//*" +
                ".xml"));*/
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new DataSourceTransactionManager(dataSource());
    }

}
