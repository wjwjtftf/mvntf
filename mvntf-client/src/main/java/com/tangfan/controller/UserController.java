/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.tangfan.client.user.User;
import com.tangfan.client.user.UserException_Exception;
import com.tangfan.client.user.UserService;
import com.tangfan.client.user.UserService_Service;
import com.tangfan.util.WebUtil;

/**
 * 用户模块的控制层
 * 
 * @author TangFan
 * @version 创建时间:2015年4月24日 下午9:27:08
 */
public class UserController implements Controller {

    private UserService port;
    private UserService_Service ws;
    private String ns = "http://www.tangfan.org/user";

    /**
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse) 
     *	处理一般性.do的请求
     * @param httpservletrequest
     * @param httpservletresponse
     * @return
     * @throws Exception
     * @author TangFan
     * @version 创建时间:2015年4月24日 下午9:27:08
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	request.setCharacterEncoding("utf-8");
	String method = request.getParameter("method");
	URL url = new URL("http://localhost:8085/soap/us?wsdl");
	QName qName = new QName(ns, "UserService");
	ws = new UserService_Service(url, qName);
	port = ws.getUserServicePort();
	if (method == null || "".equals(method)) {
	    return list(request, response);
	} else if ("login".equals(method)) {
	    login(request, response);
	} else if ("add".equals(method)) {
	    add(request, response);
	} else if ("delete".equals(method)) {
	    delete(request, response);
	}
	return null;
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) {
	try {
	    WebUtil.addLicenseHeader(port, request);
	    String username = request.getParameter("username");
	    port.delete(username);
	    response.sendRedirect("user.do");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
	try {
	    WebUtil.addLicenseHeader(port, request);
	    String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    String nickname = request.getParameter("nickname");
	    try {
		User u = new User();
		u.setId(new Random(100).nextInt());
		u.setUsername(username);
		u.setPassword(password);
		u.setNickname(nickname);
		port.add(u);
	    } catch (UserException_Exception e) {
		System.out.println(e.getMessage());
	    }
	    response.sendRedirect("user.do");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
	try {
	    try {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User u = port.login(username, password);
		request.getSession().setAttribute("user", u);
	    } catch (UserException_Exception e) {
		e.printStackTrace();
	    }
	    response.sendRedirect("user.do");
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private ModelAndView list(HttpServletRequest request,
	    HttpServletResponse response) {
	return new ModelAndView("jsp/list.jsp","users",port.list());
    }

}
