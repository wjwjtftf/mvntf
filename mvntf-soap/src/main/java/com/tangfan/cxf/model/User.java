/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.cxf.model;

/**
 * CXF测试用的实体类
 * 
 * @author TangFan
 * 
 * @version 2015年4月22日
 * 
 */
public class User {

    private int id;
    private String username;
    private String password;
    private boolean alive;
    private Sex sex;

    public enum Sex {
	男, 女
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

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public boolean isAlive() {
	return alive;
    }

    public void setAlive(boolean alive) {
	this.alive = alive;
    }

    public Sex getSex() {
	return sex;
    }

    public void setSex(Sex sex) {
	this.sex = sex;
    }

}
