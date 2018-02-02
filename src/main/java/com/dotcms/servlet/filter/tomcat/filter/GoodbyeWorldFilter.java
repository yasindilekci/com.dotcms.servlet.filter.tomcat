package com.dotcms.servlet.filter.tomcat.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.dotmarketing.util.Logger;

public class GoodbyeWorldFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {
		doLog("Init with config [" + config + "]");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		if (req instanceof HttpServletRequest) {
			doLog("GoodbyeWorldFilter request [" + ((HttpServletRequest) req).getRequestURI() + "]");
		} else {
			doLog("GoodbyeWorldFilter request [" + req + "]");
		}

		chain.doFilter(req, res);

	}

	@Override
	public void destroy() {
		doLog("Destroyed GoodbyeWorldFilter");
	}

	private void doLog(String message) {
		Logger.info(this, message);
	}

}
