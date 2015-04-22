/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.soap.model;

/**
 * 测试模型
 *
 * @author TangFan
 *
 * @version 2015年4月6日
 *
 */
public class User {
	
	private int id;
	private String username;
	private String nickname;
	private String password;
	
	public User() {
		super();
	}
	
	public User(int id, String userName, String nickName, String password) {
		super();
		this.id = id;
		this.username = userName;
		this.nickname = nickName;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
