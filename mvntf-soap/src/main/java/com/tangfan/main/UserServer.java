/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
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
