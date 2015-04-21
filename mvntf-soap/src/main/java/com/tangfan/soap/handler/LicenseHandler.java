/*******************************************************************
 * Copyright (c) 2007 arvato and others
 * All rights reserved.
 *
 * Contributors:
 * arvato Systems (Shanghai) Co., Ltd.
 * 
 ******************************************************************/
package com.tangfan.soap.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * 服务器端handler
 * 
 * @author Administrator
 * 
 * @version 2015年4月9日
 * 
 */
public class LicenseHandler implements SOAPHandler<SOAPMessageContext> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.
	 * MessageContext)
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		try {
			Boolean out = (Boolean) context.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if (!out) {
				SOAPEnvelope envelope = context.getMessage().getSOAPPart().getEnvelope();
				SOAPBody body = envelope.getBody();
				String partName = body.getChildNodes().item(0).getLocalName();
				
				/*
				 * 验证用户授权
				 */
				if("list".equals(partName) || "addUser".equals(partName)){
					SOAPHeader header = envelope.getHeader();
					if(header == null){
						SOAPFault fault = body.addFault();
						fault.setFaultString("头部信息不能为空");
						throw new SOAPFaultException(fault);
					}
					@SuppressWarnings("unchecked")
					Iterator<SOAPHeaderElement> it = header.examineAllHeaderElements();
					if(!it.hasNext()){
						SOAPFault fault = body.addFault();
						fault.setFaultString("头部信息不能为空");
						throw new SOAPFaultException(fault);
					}
					
					while (it.hasNext()) {
						SOAPHeaderElement ele = it.next();
						System.out.println("服务器接受头消息:" + ele.getTextContent());
					}
				}
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext
	 * )
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.xml.ws.handler.soap.SOAPHandler#getHeaders()
	 */
	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

}
