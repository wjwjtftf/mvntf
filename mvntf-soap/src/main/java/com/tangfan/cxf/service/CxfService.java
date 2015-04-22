/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.cxf.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * cxf测试接口类
 *
 * @author TangFan
 *
 * @version 2015年4月21日
 *
 */
@WebService
public interface CxfService {

	@WebMethod
	public @WebResult(name="hello")String sayHello(@WebParam(name="name")String name);
}
