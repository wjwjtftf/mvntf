/*******************************************************************
 * Copyright (c) 2015 tangfan
 * All rights reserved.
 *
 * Contributors:
 * all Programmer Pioneers
 * 
 ******************************************************************/
package com.tangfan.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * soap项目的配置文件
 *
 * @author TangFan
 *
 * @version 2015年5月27日
 *
 */
@Configuration
// 用于在基于Java类定义Bean配置中开启MVC支持，和XML中的<mvc:annotation-driven>功能一样；
@EnableWebMvc
@ComponentScan(value = {
		"com.tangfan"
})
public class SoapApplicationConfig {
	
}
