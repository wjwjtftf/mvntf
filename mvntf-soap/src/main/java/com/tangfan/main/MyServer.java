/*******************************************************************
 * Copyright (c) 2007 arvato and others
 * All rights reserved.
 *
 * Contributors:
 * arvato Systems (Shanghai) Co., Ltd.
 * 
 ******************************************************************/
package com.tangfan.main;

import javax.xml.ws.Endpoint;

import com.tangfan.soap.service.impl.MyServiceImpl;

/**
 * 发布服务
 * 这里有个问题，本类放在test的classes文件夹中，编排就会报错，不知道为啥??
 *
 * @author Administrator
 *
 * @version 2015年4月6日
 *
 */
public class MyServer {

	/**
	 * main 启动WebService服务
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String address = "http://localhost:8082/my/";
		Endpoint.publish(address, new MyServiceImpl());
	}

}
