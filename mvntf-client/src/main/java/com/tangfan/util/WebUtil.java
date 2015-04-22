/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.util;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import com.sun.xml.ws.api.message.Headers;
import com.sun.xml.ws.developer.WSBindingProvider;
import com.tangfan.client.user.LicenseInfo;
import com.tangfan.client.user.User;
import com.tangfan.client.user.UserService;

/**
 * Web消息的工具类
 *
 * @author TangFan
 *
 * @version 2015年4月17日
 *
 */
public class WebUtil {
	
	private static String ns = "http://www.tangfan.org/user";
	
	public static void addLicenseHeader(UserService port, HttpServletRequest request){
		try {
			//1.将一个对象转换为xml
			JAXBContext jaxb = JAXBContext.newInstance(LicenseInfo.class);
			User lu = (User) request.getSession().getAttribute("user");
			if(lu == null){
				return;
			}
			LicenseInfo info = new LicenseInfo();
			info.setLoginUser(lu);
			
			QName qName = new QName(ns, "licenseInfo");
			JAXBElement<LicenseInfo> jele = new JAXBElement<LicenseInfo>(qName, LicenseInfo.class, info);
			
			//2.转换为dom
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Marshaller mars = jaxb.createMarshaller();
			mars.setProperty(Marshaller.JAXB_FRAGMENT, true);//xml头
			mars.setProperty(Marshaller.JAXB_ENCODING, "utf-8");//编码
			mars.marshal(jele, doc);
			
			//3.通过Headers.create方法完成header的添加
			WSBindingProvider wsb = (WSBindingProvider) port;
			wsb.setOutboundHeaders(Headers.create(doc.getDocumentElement()));
			
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}
	
}
