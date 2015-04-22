/*******************************************************************
 * copyright 2015 TangFan and others
 *
 * Contributors:
 * all programmers predecessors
 * 
 ******************************************************************/
package com.tangfan.webproject.server;


import org.eclipse.jetty.server.Server;

import com.tangfan.test.jetty.JettyFactory;

/**
 * 启动jetty Server
 *
 * @author TangFan
 *
 * @version 2015年4月15日
 *
 */
public class StartServer {
	public static final int		PORT		= 8086;
	public static final String	CONTEXT		= "/client";
	public static final String	BASE_URL	= "http://localhost:8086/client/user.do";

	public static void main(String[] args) throws Exception {
		// 设定Spring的profile
//		System.setProperty("spring.profiles.active", "development");

		// 启动Jetty
		Server server = JettyFactory.createServerInSource(PORT, CONTEXT);

		try {
			server.start();
			System.out.println("Server running at " + BASE_URL);
			Runtime.getRuntime().exec("cmd.exe /c start " + BASE_URL);
			System.out.println("Hit Enter to reload the application  [-Xms256m -Xmx1024m -XX:PermSize=256m -XX:MaxPermSize=256m]");
			// 等待用户输入回车重载应用.
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					JettyFactory.reloadContext(server);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
