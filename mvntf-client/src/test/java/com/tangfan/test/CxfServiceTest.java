/*******************************************************************
 * Copyright (c) 2013 tangfan and others
 * All rights reserved.
 *
 * Contributors:
 * tangfan's Systems (Shanghai) fan.T, Ltd.
 * 
 ******************************************************************/
package com.tangfan.test;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.tangfan.client.cxf.CxfService;
import com.tangfan.client.cxf.CxfServiceImplService;

/**
 * 测试cxf
 *
 * @author TangFan
 *
 * @version 2015年4月21日
 *
 */
public class CxfServiceTest {
	
	@Test
	public void sayHello(){
		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
		fac.setAddress("http://localhost:8084/cxf");
		fac.setServiceClass(CxfService.class);
		fac.getInInterceptors().add(new LoggingInInterceptor());
		fac.getOutInterceptors().add(new LoggingOutInterceptor());
		CxfService cxf = (CxfService)fac.create();
		System.out.println(cxf.sayHello("李四"));
	}
}
