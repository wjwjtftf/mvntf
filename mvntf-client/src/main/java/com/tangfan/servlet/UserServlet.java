package com.tangfan.servlet;

import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import com.tangfan.client.user.User;
import com.tangfan.client.user.UserException_Exception;
import com.tangfan.client.user.UserService;
import com.tangfan.client.user.UserService_Service;
import com.tangfan.util.WebUtil;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService port;
	private UserService_Service ws;
	private String ns = "http://www.tangfan.org/user";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		URL url = new URL("http://localhost:8085/soap/us?wsdl");
		QName qName = new QName(ns, "UserService");
		ws = new UserService_Service(url, qName);
		port = ws.getUserServicePort();
		if(method == null || "".equals(method)){
			list(request, response);
		} else if ("login".equals(method)) {
			login(request, response);
		} else if ("add".equals(method)) {
			add(request, response);
		} else if ("delete".equals(method)) {
			delete(request, response);
		}
	}

	/**
	 * delete TODO (description of the method)
	 * 
	 * @param request
	 * @param response
	 */
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

	/**
	 * add TODO (description of the method)
	 * 
	 * @param request
	 * @param response
	 */
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

	/**
	 * login TODO (description of the method)
	 * 
	 * @param request
	 * @param response
	 */
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

	/**
	 * list 列出所有
	 * 
	 * @param request
	 * @param response
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("users", port.list());
			request.getRequestDispatcher("jsp/list.jsp").forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
