package com.yzy.heatmap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = { "com.yzy.heatmap.dao" })
@EnableCaching
public class HeatmapApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeatmapApplication.class, args);
	}

}
