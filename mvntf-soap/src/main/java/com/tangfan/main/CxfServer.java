/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.main;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.handler.Handler;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import com.tangfan.cxf.interceptor.LicenseUserInInterceptor;
import com.tangfan.cxf.service.CxfService;
import com.tangfan.cxf.service.impl.CxfServiceImpl;
import com.tangfan.soap.handler.CxfServerHandler;

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
		
		/*
		 * 通过CXF API发布服务
		 */
		String address = "http://localhost:8084/cxf";
		JaxWsServerFactoryBean fac = new JaxWsServerFactoryBean();
		fac.setAddress(address);
		fac.setServiceBean(new CxfServiceImpl());
		fac.setServiceClass(CxfService.class);
		fac.getInInterceptors().add(new LoggingInInterceptor());
		fac.getOutInterceptors().add(new LoggingOutInterceptor());
		
		startHandler(fac);
		startInterceptor(fac);
		
		fac.create();
	}
	
	/**
	 * 添加handlers到服务端
	 */
	@SuppressWarnings("rawtypes")
	public static void startHandler(JaxWsServerFactoryBean fac){
		
		List<Handler> handlers = new ArrayList<Handler>();
		handlers.add(new CxfServerHandler());
		fac.setHandlers(handlers);
	}
	
	/**
	 * 添加interceptor到服务端
	 */
	public static void startInterceptor(JaxWsServerFactoryBean fac){
		
		fac.getInInterceptors().add(new LicenseUserInInterceptor());
	}

}
