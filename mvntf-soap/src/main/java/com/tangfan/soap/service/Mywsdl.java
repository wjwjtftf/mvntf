
package com.tangfan.soap.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "mywsdl", targetNamespace = "http://www.tangfan.org/mywsdl/")
public interface Mywsdl {


    /**
     * 
     * @param b
     * @param a
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(name = "sum", targetNamespace = "")
    @RequestWrapper(localName = "add", targetNamespace = "http://www.tangfan.org/mywsdl/", className = "org.tangfan.mywsdl.Add")
    @ResponseWrapper(localName = "addResponse", targetNamespace = "http://www.tangfan.org/mywsdl/", className = "org.tangfan.mywsdl.AddResponse")
    public int add(
        @WebParam(name = "a", targetNamespace = "")
        int a,
        @WebParam(name = "b", targetNamespace = "")
        int b);

}
