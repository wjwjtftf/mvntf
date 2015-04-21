/*******************************************************************
 * Copyright (c) 2013 tangfan and others
 * All rights reserved.
 *
 * Contributors:
 * tangfan's Systems (Shanghai) fan.T, Ltd.
 * 
 ******************************************************************/
package com.tangfan.soap.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.ws.BindingType;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;

import com.sun.xml.ws.api.message.Header;
import com.sun.xml.ws.api.message.HeaderList;
import com.sun.xml.ws.developer.JAXWSProperties;
import com.tangfan.soap.dao.UserDao;
import com.tangfan.soap.exception.UserException;
import com.tangfan.soap.model.User;
import com.tangfan.soap.service.UserService;

/**
 * 学习webservice接口实现类
 * 
 * @author Administrator
 * 
 * @version 2015年4月13日
 * 
 */
@WebService(endpointInterface = "com.tangfan.soap.service.UserService",
		wsdlLocation = "WEB-INF/wsdl/user.wsdl",
		serviceName = "UserService", 
		portName = "UserServicePort",
		targetNamespace="http://www.tangfan.org/user")
@BindingType(SOAPBinding.SOAP11HTTP_MTOM_BINDING)
//@MTOM
public class UserServiceImpl implements UserService {

	private UserDao userDao = UserDao.newInstance();
	
	@Resource
	private WebServiceContext wsc;
	
	/**
	 * 添加用户
	 * @throws UserException 
	 * 
	 * @see com.tangfan.soap.service.UserService#add(com.tangfan.soap.service.User)
	 */
	@Override
	public void add(User user) throws UserException {
		checkAuthority();
		userDao.add(user);
	}

	/**
	 * 删除用户
	 * 
	 * @see com.tangfan.soap.service.UserService#delete(java.lang.String)
	 */
	@Override
	public void delete(String username) {
		try {
			checkAuthority();
			userDao.delete(username);
		} catch (UserException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有用户
	 * 
	 * @see com.tangfan.soap.service.UserService#list()
	 */
	@Override
	public List<User> list() {
		return userDao.list();
	}

	/**
	 * 用户登录
	 * @throws UserException 
	 * 
	 * @see com.tangfan.soap.service.UserService#login(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public User login(String username, String password) throws UserException {
		return userDao.login(username, password);
	}
	
	/**
	 * checkAuthority 服务端检验消息头
	 * @throws UserException 
	 * 
	 */
	private void checkAuthority() throws UserException {
		//从消息头中获取信息
		HeaderList headers = (HeaderList) wsc.getMessageContext().get(JAXWSProperties.INBOUND_HEADER_LIST_PROPERTY);
		QName qName = new QName("http://www.tangfan.org/user","licenseInfo");
		if(headers == null){
			throw new UserException("该功能需要进行权限控制");
		}
		Header header = headers.get(qName, true);
		if(header == null){
			throw new UserException("该功能需要进行权限控制");
		}
		User user = analyzeUser(header);
		User loginUser = userDao.loadUser(user.getUsername());
		if(loginUser == null){
			throw new UserException("用户权限不足");
		}
		if(!loginUser.getPassword().equals(user.getPassword())){
			throw new UserException("授权用户密码不对");
		}
	}

	/**
	 * analyzeUser Stax方式手工解析一段xml转变成user对象
	 * 
	 * @param header
	 * @return
	 */
	private User analyzeUser(Header header) {
		User u = new User();
		XMLStreamReader reader;
		try {
			reader = header.readHeader();
			while(reader.hasNext()){
				int event = reader.next();
				if(event == XMLEvent.START_ELEMENT){
					String name = reader.getName().getLocalPart();
					if("id".equals(name)){
						u.setId(Integer.valueOf(reader.getElementText()));
					} else if("username".equals(name)){
						u.setUsername(reader.getElementText());
					} else if ("password".equals(name)) {
						u.setPassword(reader.getElementText());
					} else if ("nickname".equals(name)) {
						u.setNickname(reader.getElementText());
					}
				}
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return u;
	}

	/** 
	 * webservice服务端接受二进制数据
	 * @see com.tangfan.soap.service.UserService#upload(byte[])
	 * 
	 * @param file
	 * 
	 * @author TangFan
	 *
	 * @version 2015年4月18日
	 */
	@Override
	public void upload(byte[] file) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("e:/1.jpg");
			fos.write(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 
	 * TODO (description of the method)
	 * @see com.tangfan.soap.service.UserService#binary(javax.activation.DataHandler)
	 * 
	 * @param file
	 * 
	 * @author TangFan
	 *
	 * @version 2015年4月18日
	 */
	@Override
	public void binary(DataHandler file) {
		System.out.println(file.getContentType() + "=>" + file.getDataSource().getName());
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			is = file.getDataSource().getInputStream();
			fos = new FileOutputStream("e:/1.jpg");
			byte[] buff = new byte[1024];
			while (is.read(buff) != -1) {
				fos.write(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(fos != null)fos.close();
				if(is != null)is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
