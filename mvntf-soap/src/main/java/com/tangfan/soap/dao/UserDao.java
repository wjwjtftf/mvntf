/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.soap.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tangfan.soap.exception.UserException;
import com.tangfan.soap.model.User;

/**
 * 持久化逻辑类
 *
 * @author TangFan
 *
 * @version 2015年4月13日
 *
 */
public class UserDao {
	
	private static final Map<String, User> users = new HashMap<String, User>();
	private static UserDao dao;
	
	public UserDao(){
		users.put("admin", new User(1, "admin", "超级管理员", "123"));
	}
	
	/**
	 * newInstance 单例
	 * 
	 * @return
	 */
	public static UserDao newInstance(){
		if(dao == null){
			dao = new UserDao();
		}
		return dao;
	}
	
	/**
	 * add 添加用户
	 * 
	 * @param user
	 * @throws UserException 
	 */
	public void add(User user) throws UserException{
		if(user == null){
			return;
		}
		if(users.containsKey(user.getUsername())){
			throw new UserException("用户已经存在");
		}
		users.put(user.getUsername(), user);
	}
	
	/**
	 * delete 删除一个用户
	 * 
	 * @param userName
	 */
	public void delete(String userName){
		users.remove(userName);
	}
	
	/**
	 * list 返回所有的用户
	 * 
	 * @return
	 */
	public List<User> list(){
		List<User> list = new ArrayList<User>();
		Set<String> sets = users.keySet();
		for(String key : sets){
			list.add(users.get(key));
		}
		return list;
	}
	
	public User login(String username, String password) throws UserException{
		if(!users.containsKey(username)){
			throw new UserException("用户不存在");
		}
		User u = users.get(username);
		if(!password.equals(u.getPassword())){
			throw new UserException("用户密码不正确");
		}
		return u;
	}
	
	/**
	 * loadUser 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	public User loadUser(String username){
		return users.get(username);
	}
}
