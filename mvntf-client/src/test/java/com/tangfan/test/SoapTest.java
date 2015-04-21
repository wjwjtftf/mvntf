/*******************************************************************
 * Copyright (c) 2007 arvato and others
 * All rights reserved.
 *
 * Contributors:
 * arvato Systems (Shanghai) Co., Ltd.
 * 
 ******************************************************************/
package com.tangfan.test;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tangfan.client.my.User;
import com.tangfan.util.JaxbUtil;

/**
 * soap协议测试类
 *
 * @author Administrator
 *
 * @version 2015年4月6日
 *
 */
public class SoapTest {

	private String ns1 = "http://service.soap.tangfan.com/";
	private String ns2 = "http://impl.service.soap.tangfan.com/";
	private String address = "http://localhost:8080/ns?wsdl";
	
	@Test
	public void test01(){
		try {
			MessageFactory factory = MessageFactory.newInstance();
			SOAPMessage message = factory.createMessage();
			SOAPPart part = message.getSOAPPart();
			SOAPEnvelope envelope = part.getEnvelope();
			SOAPBody body= envelope.getBody();
			
			QName qname = new QName(ns1,"add","ns");
			SOAPBodyElement ele = body.addBodyElement(qname);
			ele.addChildElement("a").setValue("12");
			ele.addChildElement("b").setValue("6");
			
			message.writeTo(System.out);
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * SAAJ通信
	 * SOAP Attachment API Java 
	 */
	@Test
	public void test02(){
		try {
			//1.创建服务
			URL url = new URL(address);
			QName serviceName = new QName(ns2, "MyServiceImplService");
			Service service = Service.create(url, serviceName);
			
			//2.创建dispatch<message>
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns2, "MyServiceImplPort"), 
					SOAPMessage.class, Service.Mode.MESSAGE);
			
			//3.创建message
			SOAPMessage message = MessageFactory.newInstance().createMessage();
			SOAPBody body = message.getSOAPPart().getEnvelope().getBody();
			//这里的qname必须指定前缀，因为在soapenvelope中没有定义默认命名空间
			SOAPBodyElement ele = body.addBodyElement(new QName(ns1, "add", "ns"));
			ele.addChildElement("a").setValue("22");
			ele.addChildElement("b").setValue("6");
			message.writeTo(System.out);
			System.out.println("\n invoking......");
			
			//4.发送消息
			SOAPMessage response = dispatch.invoke(message);
			response.writeTo(System.out);
			
			//5.获取返回结果
			Document doc = response.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			System.out.println("\n返回结果是:" + doc.getElementsByTagName("addResult").item(0).getTextContent());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test03(){
		try {
			//1.创建服务
			URL url = new URL(address);
			QName serviceName = new QName(ns2, "MyServiceImplService");
			Service service = Service.create(url, serviceName);
			
			//2.创建dispatch<负载>
			Dispatch<Source> dispatch = service.createDispatch(new QName(ns2, "MyServiceImplPort"), 
					Source.class, Service.Mode.PAYLOAD);
			
			//3.创建字符串
			User user = new User();
			user.setId(3);
			user.setNickname("张三");
			user.setUsername("zs");
			user.setPassword("111111");
			String userStr = JaxbUtil.convertToXml(user);
			
			//4.封装相应的Body
			String payLoad = "<nn:addUser xmlns:nn=\"" + ns1 + "\">" + userStr + "</nn:addUser>";
			System.out.println(payLoad);
			StreamSource rs = new StreamSource(new StringReader(payLoad));
			
			//5.通过dispatch传递payload
			Source response = dispatch.invoke(rs);
			
			//6.处理响应信息
			Transformer trans = TransformerFactory.newInstance().newTransformer();
			DOMResult result = new DOMResult();
			trans.transform(response, result);
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			NodeList nl = (NodeList) xPath.evaluate("//user", result.getNode(), XPathConstants.NODESET);
			
			//7.反编排
			Unmarshaller unmarshaller = JAXBContext.newInstance(User.class).createUnmarshaller();
			User rUser = (User) unmarshaller.unmarshal(nl.item(0));
			System.out.println(rUser.getNickname());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test04(){
		try {
			//1.创建服务
			URL url = new URL(address);
			QName serviceName = new QName(ns2, "MyServiceImplService");
			Service service = Service.create(url, serviceName);
			
			//2.创建dispatch<message>
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(ns2, "MyServiceImplPort"), 
					SOAPMessage.class, Service.Mode.MESSAGE);
			
			//3.创建message
			SOAPMessage message = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();
			
			SOAPHeader header = envelope.getHeader();
			header = header == null ? envelope.addHeader() : header;
			//这里的qname必须指定前缀，因为在soapenvelope中没有定义默认命名空间
			header.addHeaderElement(new QName(ns1, "authInfo", "nn")).setValue("aaabbbccc");
			body.addBodyElement(new QName(ns1, "list", "ns"));
			message.writeTo(System.out);
			System.out.println("\n invoking......");
			
			//4.发送消息
			SOAPMessage response = dispatch.invoke(message);
			response.writeTo(System.out);
			System.out.println();
			
			//5.获取返回结果
			Document doc = response.getSOAPBody().extractContentAsDocument();
			NodeList nl = doc.getElementsByTagName("user");
			Unmarshaller unmarshaller = JAXBContext.newInstance(User.class).createUnmarshaller();
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				User u = (User) unmarshaller.unmarshal(node);
				System.out.println(u.getNickname());
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (SOAPException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
