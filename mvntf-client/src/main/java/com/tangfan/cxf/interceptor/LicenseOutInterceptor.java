/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.cxf.interceptor;

import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.databinding.DataBinding;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.apache.cxf.phase.Phase;

/**
 * cxf支持的客户端出口interceptor
 *
 * @author TangFan
 *
 * @version 2015年4月22日
 *
 */
public class LicenseOutInterceptor extends AbstractSoapInterceptor {

	/**
	 * 这里是最重要的，需要覆盖父类的构造函数
	 * @param p	说明使用的阶段
	 */
	public LicenseOutInterceptor(String phase) {
		super(phase);
	}
	
	/**
	 * 默认构造方法
	 */
	public LicenseOutInterceptor() {
		super(Phase.WRITE);
	}
	
	/** 
	 * CXF提供的客户端出口拦截器
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	
	 * @param message
	 * @throws Fault
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		try {
			List<Header> headers = message.getHeaders();
			QName qn = new QName("http://service.cxf.tangfan.com/", "licenseInfo");
			DataBinding binding = new JAXBDataBinding(String.class);
			Header header = new Header(qn, "admin_interceptor", binding);
			headers.add(header);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
