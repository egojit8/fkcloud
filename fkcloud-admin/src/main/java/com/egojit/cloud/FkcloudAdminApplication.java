package com.egojit.cloud;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * fkcloud接口程序
 * @author egojit. QQ:408365330
 */
@tk.mybatis.spring.annotation.MapperScan(basePackages = "com.egojit.cloud.mapper")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableAsync
public class FkcloudAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(FkcloudAdminApplication.class, args);
	}
}
