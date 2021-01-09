package com.tao.cloud2020.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.tao.cloud2020.dao"})
public class MyBatisConfig {
}
