/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.cxf.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * 客户端Handler
 *
 * @author TangFan
 *
 * @version 2015年4月22日
 *
 */
public class LicenseInfoHandler implements SOAPHandler<SOAPMessageContext> {

	/** 
	 * TODO (description of the method)
	 * @see javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
	
	 * @param arg0
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * TODO (description of the method)
	 * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
	
	 * @param arg0
	 * @return
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 
	 * 处理正常消息
	 * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
	
	 * @param arg0
	 * @return
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext ctx) {
		try {
			Boolean out = (Boolean) ctx.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if(out){
				SOAPEnvelope enve = ctx.getMessage().getSOAPPart().getEnvelope();
				SOAPHeader header = enve.getHeader();
				if(header == null){
					header = enve.addHeader();
				}
				QName qName = new QName("http://service.cxf.tangfan.com/", "licenseInfo");
				header.addHeaderElement(qName).setValue("admin123");
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}

	/** 
	 * TODO (description of the method)
	 * @see javax.xml.ws.handler.soap.SOAPHandler#getHeaders()
	
	 * @return
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
