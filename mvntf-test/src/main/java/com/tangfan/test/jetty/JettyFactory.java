/**
 * Copyright (c) 2005-2012 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.tangfan.test.jetty;

import java.util.List;

import com.google.common.collect.Lists;

import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.SecurityHandler;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppClassLoader;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * 创建Jetty Server的工厂类
 *
 * @author calvin
 */
public class JettyFactory {

	private static final String DEFAULT_WEBAPP_PATH = "src/main/webapp";
	private static final String WINDOWS_WEBDEFAULT_PATH = "jetty/webdefault-windows.xml";
	private static final String REALM_PATH = "src/main/resources/jetty/realm.properties";

	/**
	 * 创建用于开发运行调试的Jetty Server, 以src/main/webapp为Web应用目录.
	 */
	public static Server createServerInSource(int port, String contextPath) {
		Server server = new Server();
		//设置在JVM退出时关闭Jetty的钩子。
		server.setStopAtShutdown(true);

		SelectChannelConnector connector = new SelectChannelConnector();
		connector.setPort(port);
		//解决Windows下重复启动Jetty居然不报告端口冲突的问题.
		connector.setReuseAddress(false);
		server.setConnectors(new Connector[] { connector });

		WebAppContext webContext = new WebAppContext(DEFAULT_WEBAPP_PATH, contextPath);
		//修改webdefault.xml，解决Windows下Jetty Lock住静态文件的问题.
		
		
		//重新加载登录认证的REALM文件
		SecurityHandler securityHandler = new ConstraintSecurityHandler(); 		
		HashLoginService hashLoginService =new HashLoginService();
		hashLoginService.setConfig(REALM_PATH);
		hashLoginService.setName("MyRealm");
		securityHandler.setLoginService(hashLoginService);
        webContext.setSecurityHandler(securityHandler);
        
        //设置表单提交最大数据量限制 (-1没有限制,默认为1000)
    	webContext.setMaxFormKeys(-1);
    	webContext.setMaxFormContentSize(-1);
    	
    	System.out.println("---------MaxFormKeys--------:" + webContext.getMaxFormKeys());
        System.out.println("---------MaxFormContentSize--------:" + webContext.getMaxFormContentSize());
    	
        webContext.setDefaultsDescriptor(WINDOWS_WEBDEFAULT_PATH);
        server.setHandler(webContext);
       		return server;
	}

	/**
	 * 设置除jstl-*.jar外其他含tld文件的jar包的名称.
	 * jar名称不需要版本号，如sitemesh, shiro-web
	 */
	public static void setTldJarNames(Server server, String... jarNames) {
		WebAppContext context = (WebAppContext) server.getHandler();
		List<String> jarNameExprssions = Lists.newArrayList(".*/jstl-[^/]*\\.jar$", ".*/.*taglibs[^/]*\\.jar$");
		for (String jarName : jarNames) {
			jarNameExprssions.add(".*/" + jarName + "-[^/]*\\.jar$");
		}

		context.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				StringUtils.join(jarNameExprssions, '|'));

	}

	/**
	 * 快速重新启动application，重载target/classes与target/test-classes.
	 */
	public static void reloadContext(Server server) throws Exception {
		WebAppContext context = (WebAppContext) server.getHandler();

		System.out.println("Application reloading");
		context.stop();

		WebAppClassLoader classLoader = new WebAppClassLoader(context);
		classLoader.addClassPath("target/classes");
		classLoader.addClassPath("target/test-classes");
		context.setClassLoader(classLoader);

		context.start();

		System.out.println("Application reloaded");
	}
}
