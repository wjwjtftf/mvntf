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
import com.tangfan.cxf.interceptor.LicenseOutInterceptor;
import com.tangfan.cxf.interceptor.LicenseUserOutInterceptor;

/**
 * 测试cxf
 * 
 * @author TangFan
 * 
 * @version 2015年4月21日
 * 
 */
public class CxfServiceTest {

	/**
	 * sayHello 测试handler处理消息头
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void sayHello() {

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
		CxfService cxf = (CxfService) fac.create();
		System.out.println(cxf.sayHello("李四"));
	}

	/**
	 * sayHelloInterceptor 测试interceptor处理消息头
	 */
	@Test
	public void sayHelloStringInter() {

		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
		fac.setAddress("http://localhost:8084/cxf");
		fac.setServiceClass(CxfService.class);
		fac.getInInterceptors().add(new LoggingInInterceptor());
		fac.getOutInterceptors().add(new LoggingOutInterceptor());

		/*
		 * 这里添加一个自定义的拦截器
		 */
		fac.getOutInterceptors().add(new LicenseOutInterceptor());

		CxfService cxf = (CxfService) fac.create();
		System.out.println(cxf.sayHello("李四"));
		
	}
	
	/**
	 * sayHelloInterceptor 测试interceptor处理消息头
	 */
	@Test
	public void sayHelloUserInter() {

		JaxWsProxyFactoryBean fac = new JaxWsProxyFactoryBean();
		fac.setAddress("http://localhost:8085/soap/services/cxf");
		fac.setServiceClass(CxfService.class);
		fac.getInInterceptors().add(new LoggingInInterceptor());
		fac.getOutInterceptors().add(new LoggingOutInterceptor());

		/*
		 * 这里添加一个自定义的拦截器
		 */
		fac.getOutInterceptors().add(new LicenseUserOutInterceptor());

		CxfService cxf = (CxfService) fac.create();
		System.out.println(cxf.sayHello("李四"));
		
	}
}
