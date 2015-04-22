/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.soap.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import com.tangfan.soap.exception.UserException;
import com.tangfan.soap.model.User;
import com.tangfan.soap.service.MyService;

/**
 * WebService接口实现类
 *
 * @author Administrator
 *
 * @version 2015年4月6日
 *
 */
@WebService(endpointInterface="com.tangfan.soap.service.MyService")
@HandlerChain(file="handler-chain.xml")
public class MyServiceImpl implements MyService {
	
	private static List<User> users = new ArrayList<User>();

	public MyServiceImpl(){
		users.add(new User(1, "admin", "管理员", "111111"));
	}
	
	/* (non-Javadoc)
	 * @see com.tangfan.soap.service.MyService#add(int, int)
	 */
	@Override
	public int add(int a, int b) {
		System.out.println("a + b = " + (a + b));
		return a + b;
	}

	/* (non-Javadoc)
	 * @see com.tangfan.soap.service.MyService#addUser(com.tangfan.soap.model.User)
	 */
	@Override
	public User addUser(User user) {
		users.add(user);
		return user;
	}

	/* (non-Javadoc)
	 * @see com.tangfan.soap.service.MyService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String userName, String password) throws UserException {
		for (User user : users) {
			if(user.getUsername().equals(userName) && user.getPassword().equals(password)){
				return user;
			}
		}
		throw new UserException("用户不存在");
	}

	/* (non-Javadoc)
	 * @see com.tangfan.soap.service.MyService#list()
	 */
	@Override
	public List<User> list(String authInfo) {
		System.out.println(authInfo);
		return users;
	}

}
