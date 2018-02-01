package com.dotmarketing.osgi.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.dotmarketing.util.Logger;

public class HelloWorldFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		doLog("Init with config [" + config + "]");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		if (req instanceof HttpServletRequest) {
			doLog("HelloWorldFilter request [" + ((HttpServletRequest) req).getRequestURI() + "]");
		} else {
			doLog("HelloWorldFilter request [" + req + "]");
		}

		chain.doFilter(req, res);

	}

	@Override
	public void destroy() {
		doLog("Destroyed HelloWorldFilter");
	}

	private void doLog(String message) {
		Logger.info(this, message);
	}

}
