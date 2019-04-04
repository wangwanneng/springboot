package com.wwn.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.wwn.dao.orders",sqlSessionFactoryRef = "testCrmSqlSessionFactory")
public class DataSourceCrmConfig {


    /*分布式事务*/
    @Bean(name = "testCrmDataSource")
    public DataSource testDataSource(DBConfigCrm dbConfigCrm) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(dbConfigCrm.getJdbcUrl());
        mysqlXADataSource.setUser(dbConfigCrm.getUsername());
        mysqlXADataSource.setPassword(dbConfigCrm.getPassword());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("testCrmDataSource");
        return atomikosDataSourceBean;
    }


    /*@Bean(name = "testCrmDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.crm")
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }*/


    @Bean(name="testCrmSqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("testCrmDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/orders/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /*@Bean(name="testCrmTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("testCrmDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "testCrmSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("testCrmSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
