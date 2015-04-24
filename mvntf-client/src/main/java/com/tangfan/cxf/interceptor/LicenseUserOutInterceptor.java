/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.cxf.interceptor;

import java.util.List;
import java.util.Random;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;

import com.tangfan.cxf.model.User;

/**
 * CXF提供的客户端出口拦截器
 *
 * @author TangFan
 *
 * @version 2015年4月22日
 *
 */
public class LicenseUserOutInterceptor extends AbstractSoapInterceptor {

	/**
	 * 默认的构造方法，用来绑定在READ Pahse上
	 */
	public LicenseUserOutInterceptor() {
		super(Phase.WRITE);
	}
	
	/** 
	 * 处理出口的soap消息
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	
	 * @param message	SoapMessage
	 * @throws Fault
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@Override
	public void handleMessage(SoapMessage message) throws Fault {

		try {
			QName qName = new QName("http://service.cxf.tangfan.com/", "licenseInfo");
			User user = new User();
			user.setId(new Random(10000).nextInt());
			user.setAlive(true);
			user.setPassword("123456");
			user.setSex(User.Sex.女);
			DataBinding binding = new JAXBDataBinding(User.class);
			
			Header header = new Header(qName, user, binding);
			List<Header> headers = message.getHeaders();
			headers.add(header);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

	}

}
