package com.sdau.housesManage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.sdau.housesManage.dao"})
public class HousesManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(HousesManageApplication.class, args);
	}

}
