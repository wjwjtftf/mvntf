/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.MTOMFeature;
import javax.xml.ws.soap.SOAPBinding;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.tangfan.client.user.LicenseInfo;
import com.tangfan.client.user.User;
import com.tangfan.client.user.UserException_Exception;
import com.tangfan.client.user.UserService;
import com.tangfan.client.user.UserService_Service;


/**
 * UserService的测试类
 *
 * @author TangFan
 *
 * @version 2015年4月13日
 *
 */
public class UserServiceTest {
	
	private UserService port;
	private UserService_Service ws;
	private String ns = "http://www.tangfan.org/user";
	
	@Before
	public void init(){
		try {
			URL url = new URL("http://localhost:8085/soap/us?wsdl");
			QName qName = new QName(ns, "UserService");
			ws = new UserService_Service(url, qName);
//			port = ws.getUserServicePort(new MTOMFeature());
			port = ws.getUserServicePort();
			BindingProvider bp = (BindingProvider)port;
			SOAPBinding binding = (SOAPBinding) bp.getBinding();
			binding.setMTOMEnabled(true);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * list 测试web方式访问
	 */
	@Test
	public void list(){
		List<User> list = port.list();
		for(User u : list){
			System.out.println(u.getNickname());
		}
	}
	
	/**
	 * add 测试jaxws-ri方式操作消息头
	 */
	@Test
	public void add(){
		try {
			//1.将一个对象转换为xml
			JAXBContext jaxb = JAXBContext.newInstance(LicenseInfo.class);
			User lu = new User();
			lu.setId(0);
			lu.setNickname("茶几");
			lu.setUsername("admin");
			lu.setPassword("123");
			LicenseInfo info = new LicenseInfo();
			info.setLoginUser(lu);
			
			QName qName = new QName(ns, "licenseInfo");
			JAXBElement<LicenseInfo> jele = new JAXBElement<LicenseInfo>(qName, LicenseInfo.class, info);
			
			Marshaller mars = jaxb.createMarshaller();
			mars.setProperty(Marshaller.JAXB_FRAGMENT, true);//xml头
			mars.setProperty(Marshaller.JAXB_ENCODING, "utf-8");//编码
			
			//2.转换为dom
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			mars.marshal(jele, doc);
			
			//3.通过Headers.create方法完成header的添加
			WSBindingProvider wsb = (WSBindingProvider) port;
			wsb.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
			
			User u = new User();
			u.setId(2);
			u.setUsername("master");
			u.setNickname("jboss管理员");
			u.setPassword("123456");
			port.add(u);
		} catch (UserException_Exception e) {
			System.out.println(e.getMessage());
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * testUpload 测试webservice传递二进制数据
	 */
	@Test
	public void testUpload(){
		try {
			byte[] file = FileUtils.readFileToByteArray(new File("C:/Users/Administrator/Pictures/QQ图片20150206132707.jpg"));
			port.upload(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * testBinary 测试webservice传递二进制数据
	 */
	@Test
	public void testBinary(){
		DataHandler file = new DataHandler(new FileDataSource(new File("C:/Users/Administrator/Pictures/QQ图片20150206132707.jpg")));
		port.binary(file);
	}
	
}
