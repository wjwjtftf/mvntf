/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.soap.handler;

import java.util.Iterator;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * 服务器端解析头信息的cxf专用的handler
 * (其实普通jaxws-ri也可以用啦)
 *
 * @author TangFan
 *
 * @version 2015年4月22日
 *
 */
public class CxfServerHandler implements SOAPHandler<SOAPMessageContext> {

	/** 
	 * 处理正常的消息
	 * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
	
	 * @param context
	 * @return
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean handleMessage(SOAPMessageContext cxt) {
		try {
			Boolean out = (Boolean) cxt.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
			if(!out){
				SOAPEnvelope enve = cxt.getMessage().getSOAPPart().getEnvelope();
				SOAPHeader header = enve.getHeader();
				if(header == null){
					return true;
				}
				Iterator<SOAPHeaderElement> it = header.getChildElements();
				while (it.hasNext()) {
					SOAPHeaderElement ele = it.next();
					if(ele.getLocalName().equals("licenseInfo")){
						System.out.println("服务器接受到licenseInfo:" + ele.getTextContent());
					}
				}
			}
		} catch (SOAPException e) {
			e.printStackTrace();
		}
		return true;
	}

	/** 
	 * TODO (description of the method)
	 * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
	
	 * @param context
	 * @return
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 
	 * TODO (description of the method)
	 * @see javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
	
	 * @param context
	
	 * @author TangFan
	 *
	 * @version 2015年4月22日
	 */
	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub
		
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
