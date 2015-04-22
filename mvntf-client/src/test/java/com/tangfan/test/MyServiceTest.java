/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.test;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import javax.xml.ws.soap.SOAPFaultException;

import org.junit.Test;

import com.tangfan.client.my.MyService;
import com.tangfan.client.my.MyServiceImplService;
import com.tangfan.client.my.User;

/**
 * MyService客户端测试类
 *
 * @author Administrator
 *
 * @version 2015年4月9日
 *
 */
public class MyServiceTest {
	
	@Test
	public void test01(){
		try {
			URL url = new URL("http://localhost:8082/my/?wsdl");
			QName qName = new QName("http://impl.service.soap.tangfan.com/", "MyServiceImplService");
			MyServiceImplService mis = new MyServiceImplService(url, qName);
			MyService ms = mis.getMyServiceImplPort();
//			ms.login("admin", "11111");
			User u = new User();
			u.setId(2);
			u.setNickname("特派员");
			u.setPassword("111111");
			u.setUsername("soap");
			Holder<User> user = new Holder<User>(u);
			ms.addUser(user);
			System.out.println("#end.");
		} catch (SOAPFaultException e) {
			System.out.println(e.getMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
