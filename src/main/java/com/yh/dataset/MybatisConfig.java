package com.yh.dataset;

import com.yh.dataset.DynamicDataSourceContextHolder.DataSourceType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 解析配置项
 * @Author: yinhui
 * @Date: 2019/4/24 10:08
 * @Version 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.yh.dao"})
@AutoConfigureAfter({ MybatisAutoConfiguration.class })
public class MybatisConfig {

    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Primary
    @Bean(name = "writeDataSource")
    @ConfigurationProperties(prefix = "datasource.write-config")
    public DataSource writeDataSource(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 有多少从库就需要配置多少个
     * @return
     */
    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "datasource.read-config")
    public DataSource readDataSourceOne(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource2")
    @ConfigurationProperties(prefix = "datasource.read-config2")
    public DataSource readDataSourceTwo(){
        return DataSourceBuilder.create().type(dataSourceType).build();
    }


    /**
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("writeDataSource") DataSource writeDataSource,
                                               @Qualifier("readDataSource1") DataSource readDataSourceOne,
                                               @Qualifier("readDataSource2") DataSource readDataSourceTwo){

        Map<Object,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.WRITE.name(),writeDataSource);
        targetDataSources.put(DataSourceType.READONE.name(),readDataSourceOne);
        targetDataSources.put(DataSourceType.READTWO.name(),readDataSourceTwo);

        // 将 Slave 数据源的 key 放在集合中，用于轮循
        DynamicDataSourceContextHolder.slaveDataSourceKey.add(DataSourceType.READONE.name());
        DynamicDataSourceContextHolder.slaveDataSourceKey.add(DataSourceType.READTWO.name());

        DynamicDataSource proxy  = new DynamicDataSource();
        //默认一个数据源
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);

        return proxy;
    }

    /**
     * 重写SqlSessionFactory,根据数据源创建 SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource,
            @Value("${mybatis.mapper-locations}") String mapperLocations) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dynamicDataSource);
        // 用于获取*.xml文件，不加mapper找不到xml
        sessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return sessionFactoryBean.getObject();
    }

    /**
     * 自定义事务
     * MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源
     * 与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
     * 只有让 MyBatis 参与到 Spring 的事务管理中 ，Spring 的 @Transactional 注解才会起作用
     */
    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource){

        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
