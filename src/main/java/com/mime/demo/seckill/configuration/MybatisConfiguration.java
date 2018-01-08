package com.mime.demo.seckill.configuration;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author SlumDuck
 * @create 2018-01-08 15:05
 * @desc mybatis配置信息
 */

@Configuration
@EnableTransactionManagement
@AutoConfigureAfter(DataSourceConfiguration.class)
public class MybatisConfiguration implements TransactionManagementConfigurer {

    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    @Resource
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});

        //sqlSessionFactoryBean.setTypeAliasesPackage("tk.mybatis.springboot.model");
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        //在取得的值为null时也调用 setter
        sqlSessionFactory.getConfiguration().setCallSettersOnNulls(true);

        //开启二级缓存，注意mapper.xml文件中需要使用cache标签来开启
        sqlSessionFactory.getConfiguration().setCacheEnabled(true);

        //禁用延迟加载
        sqlSessionFactory.getConfiguration().setLazyLoadingEnabled(false);
        //加载所有属性
        sqlSessionFactory.getConfiguration().setAggressiveLazyLoading(true);
        return sqlSessionFactory;
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
