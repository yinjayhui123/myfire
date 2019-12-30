package com.yh;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.github.pagehelper.PageHelper;
import com.yh.bean.StudentBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages = {"com.yh"})
@MapperScan(basePackages = {"com.yh.dao"})
@EnableMethodCache(basePackages = "com.yh")
@EnableCreateCacheAnnotation
@EnableCaching
public class MyfireApplication {

	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(MyfireApplication.class);
//		app.setBannerMode(Banner.Mode.OFF);
//		app.run(args);
		SpringApplication.run(MyfireApplication.class, args);

	}

//	@Bean
//	public RocketMQAutoConfiguration rocketMQTemplate(){
//		return  new RocketMQAutoConfiguration();
//	}

	@Bean
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}

	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties p = new Properties();
		p.setProperty("offsetAsPageNum","true");
		p.setProperty("rowBoundsWithCount", "true");
		p.setProperty("reasonable", "true");
		pageHelper.setProperties(p);
		return pageHelper;
	}

}
