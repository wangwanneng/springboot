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
@MapperScan(basePackages = "com.wwn.dao.users",sqlSessionFactoryRef = "testEnjoySqlSessionFactory")
public class DataSourceEnjoyConfig {

    @Bean(name = "testEnjoyDataSource")
    @Primary
    public DataSource testDataSource(DBConfigEnjoy dbConfigEnjoy) {
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(dbConfigEnjoy.getJdbcUrl());
        mysqlXADataSource.setUser(dbConfigEnjoy.getUsername());
        mysqlXADataSource.setPassword(dbConfigEnjoy.getPassword());

        AtomikosDataSourceBean atomikosDataSourceBean = new AtomikosDataSourceBean();
        atomikosDataSourceBean.setXaDataSource(mysqlXADataSource);
        atomikosDataSourceBean.setUniqueResourceName("testEnjoyDataSource");
        return atomikosDataSourceBean;
    }



    /*没有使用事务
    @Bean(name = "testEnjoyDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.enjoy")
    @Primary
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }*/


    @Bean(name="testEnjoySqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("testEnjoyDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/users/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

   /* @Bean(name="testEnjoyTransactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("testEnjoyDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "testEnjoySqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("testEnjoySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
