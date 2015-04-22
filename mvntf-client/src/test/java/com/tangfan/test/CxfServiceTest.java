/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.test;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import com.tangfan.client.cxf.CxfService;
import com.tangfan.cxf.handler.LicenseInfoHandler;

/**
 * 测试cxf
 *
 * @author TangFan
 *
 * @version 2015年4月21日
 *
 */
public class CxfServiceTest {
	
	@SuppressWarnings("rawtypes")
	@Test
	public void sayHello(){
		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
		fac.setAddress("http://localhost:8084/cxf");
		fac.setServiceClass(CxfService.class);
		fac.getInInterceptors().add(new LoggingInInterceptor());
		fac.getOutInterceptors().add(new LoggingOutInterceptor());
		/*
		 * 这里不需要handler-chain.xml文件就可以添加handler了，cxf提供的方式
		 */
		List<Handler> handlers = new ArrayList<Handler>();
		handlers.add(new LicenseInfoHandler());
		fac.setHandlers(handlers);
		CxfService cxf = (CxfService)fac.create();
		System.out.println(cxf.sayHello("李四"));
	}
}
