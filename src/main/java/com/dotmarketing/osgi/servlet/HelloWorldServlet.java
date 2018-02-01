package com.dotmarketing.osgi.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebListener
public class HelloWorldServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
	}

	@Override
	public void init() throws ServletException {

		super.init();
	}

	private static final long serialVersionUID = 42L;

	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

		httpServletResponse.setContentType("text/html");

		ServletOutputStream out = httpServletResponse.getOutputStream();

		out.println("<html><body>");
		out.println("HERE I AM BABY");

		out.println("</body></html>");
		out.close();
	}

}