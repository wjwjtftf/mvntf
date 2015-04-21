/*******************************************************************
 * Copyright (c) 2013 tangfan and others
 * All rights reserved.
 *
 * Contributors:
 * tangfan's Systems (Shanghai) fan.T, Ltd.
 * 
 ******************************************************************/
package com.tangfan.cxf.service.impl;

import javax.jws.WebService;

import com.tangfan.cxf.service.CxfService;

/**
 * cxf测试实现类
 *
 * @author TangFan
 *
 * @version 2015年4月21日
 *
 */
@WebService(endpointInterface="com.tangfan.cxf.service.CxfService",
		targetNamespace="http://service.cxf.tangfan.com/")
public class CxfServiceImpl implements CxfService {

	/** 
	 * 一个简单方法
	 * @see com.tangfan.cxf.service.CxfService#sayHello(java.lang.String)
	
	 * @param name
	 * @return
	
	 * @author TangFan
	 *
	 * @version 2015年4月21日
	 */
	@Override
	public String sayHello(String name) {
		System.out.println(name);
		return "hello," + name;
	}

}
