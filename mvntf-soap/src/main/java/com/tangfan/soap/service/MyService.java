/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.soap.service;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.tangfan.soap.exception.UserException;
import com.tangfan.soap.model.User;

/**
 * WebService接口类
 *
 * @author Administrator
 *
 * @version 2015年4月6日
 *
 */
@WebService
public interface MyService {

	@WebResult(name="addResult")
	public int add(@WebParam(name="a")int a, @WebParam(name="b")int b);
	
	@WebResult(name="user")
	public User addUser(@WebParam(name="user")User user);
	
	@WebResult(name="user")
	public User login(@WebParam(name="userName")String userName, @WebParam(name="password")String password) throws UserException;
	
	@WebResult(name="user")
	public List<User> list(@WebParam(header=true,name="authInfo")String aurhInfo);
	
}
