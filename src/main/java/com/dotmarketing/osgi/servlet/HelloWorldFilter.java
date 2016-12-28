package com.dotmarketing.osgi.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloWorldFilter implements Filter {


  @Override
  public void init(FilterConfig config) throws ServletException {

    doLog("Init with config [" + config + "]");
  }

  @Override
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
      throws IOException, ServletException {

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
    System.err.println("## [" + this.getClass() + "] " + message);
  }

}
