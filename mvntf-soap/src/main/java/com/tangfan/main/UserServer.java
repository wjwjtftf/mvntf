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

import com.tangfan.soap.service.impl.UserServiceImpl;

/**
 * 发布服务
 *
 * @author Administrator
 *
 * @version 2015年4月6日
 *
 */
public class UserServer {

	/**
	 * main 启动WebService服务
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String address = "http://localhost:8083/us/";
		Endpoint.publish(address, new UserServiceImpl());
	}

}
