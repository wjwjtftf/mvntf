/*******************************************************************
 * Copyright (c) 2007 arvato and others
 * All rights reserved.
 *
 * Contributors:
 * arvato Systems (Shanghai) Co., Ltd.
 * 
 ******************************************************************/
package com.tangfan.util;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Jaxb 2.0工具类
 * 需要注意的是支持jdk1.6及以上的版本
 *
 * @author zhuc (百度找的)
 *
 * @create 2013-3-29 下午2:40:14
 *
 * @version 2014年12月25日
 *	
 */
public class JaxbUtil {

	/**
	 * 
	 * convertToXml JavaBean转换成xml
	 * 默认编码UTF-8
	 * @param obj
	 * @return
	 */
    public static String convertToXml(Object obj) {  
        return convertToXml(obj, "UTF-8");  
    }
    
    
	/**
	 * 
	 * convertToXml JavaBean转换成xml
	 * 
	 * @param obj
	 * @param encoding
	 * @return
	 */
	public static String convertToXml(Object obj, String encoding) {
		String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            
            Marshaller marshaller = context.createMarshaller();
            /*
             * 转换成xml时同时进行格式化(就是换行啊~)
             */
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            /*
             * xml的编码方式
             */
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);

            StringWriter writer = new StringWriter();
            /*
             * 编排
             */
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
	}
	
	/**
	 * 
	 * converyToJavaBean xml转换成JavaBean
	 * 
	 * @param xml
	 * @param c
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T converyToJavaBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            /*
             * 反编排
             */
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
}
