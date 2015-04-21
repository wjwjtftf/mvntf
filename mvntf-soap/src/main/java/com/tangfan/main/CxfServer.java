/*******************************************************************
 * Copyright (c) 2013 tangfan and others
 * All rights reserved.
 *
 * Contributors:
 * tangfan's Systems (Shanghai) fan.T, Ltd.
 * 
 ******************************************************************/
package com.tangfan.main;

import javax.xml.ws.Endpoint;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.tangfan.cxf.service.CxfService;
import com.tangfan.cxf.service.impl.CxfServiceImpl;

/**
 * CxfServiceImpl服务的发布
 *
 * @author TangFan
 *
 * @version 2015年4月21日
 *
 */
public class CxfServer {

	/**
	 * main 启动服务
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String address = "http://localhost:8084/cxf";
//		Endpoint.publish(address, new CxfServiceImpl());
		
		/*
		 * 另一种发布的方式
		 */
		JaxWsServerFactoryBean fac = new JaxWsServerFactoryBean();
		fac.setAddress(address);
		fac.setServiceBean(new CxfServiceImpl());
		fac.setServiceClass(CxfService.class);
		fac.getInInterceptors().add(new LoggingInInterceptor());
		fac.getOutInterceptors().add(new LoggingOutInterceptor());
		fac.create();
	}

}
