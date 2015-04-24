/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.cxf.interceptor;

import java.util.List;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * CXF提供的服务端进口拦截器
 * 
 * @author TangFan
 * 
 * @version 2015年4月22日
 * 
 */
public class LicenseUserInInterceptor extends AbstractSoapInterceptor {

    /**
     * 默认的构造方法，用来绑定在READ Pahse上
     */
    public LicenseUserInInterceptor() {
	super(Phase.INVOKE);
    }

    /**
     * 处理进口的soap消息
     * 
     * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
     * 
     * @param message
     *            SoapMessage
     * @throws Fault
     * 
     * @author TangFan
     * 
     * @version 2015年4月22日
     */
    @Override
    public void handleMessage(SoapMessage message) throws Fault {

	List<Header> headers = message.getHeaders();
	Object o = null;
	for (Header header : headers) {
	    o = header.getObject();
	    if (header.getName().getLocalPart().equals("licenseInfo")) {
		Element ele = (Element) o;
		Node id = ele.getElementsByTagName("id").item(0);
		Node sex = ele.getElementsByTagName("sex").item(0);
		System.out.println(id.getTextContent() + "==" + sex.getTextContent());
	    }
	}

    }

}
