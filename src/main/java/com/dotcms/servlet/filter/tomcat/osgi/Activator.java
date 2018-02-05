package com.dotcms.servlet.filter.tomcat.osgi;

import org.osgi.framework.BundleContext;

import com.dotcms.repackage.org.apache.logging.log4j.LogManager;
import com.dotcms.repackage.org.apache.logging.log4j.core.LoggerContext;
import com.dotcms.servlet.filter.tomcat.filter.GoodbyeWorldFilter;
import com.dotcms.servlet.filter.tomcat.filter.HelloWorldFilter;
import com.dotcms.servlet.filter.tomcat.servlet.GoodbyeWorldServlet;
import com.dotcms.servlet.filter.tomcat.servlet.HelloWorldServlet;
import com.dotcms.servlet.filter.tomcat.util.FilterOrder;
import com.dotcms.servlet.filter.tomcat.util.TomcatServletFilterUtil;
import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

public class Activator extends GenericBundleActivator {

	private static final String HELLO_WORLD_FILTER_NAME = "helloWorldFilter";
	private static final String HELLO_WORLD_SERVLET_NAME = "helloWorldServlet";

	private static final String GOODBYE_WORLD_FILTER_NAME = "goodbyeWorldFilter";
	private static final String GOODBYE_WORLD_SERVLET_NAME = "goodbyeWorldServlet";

	private LoggerContext pluginLoggerContext;

	private TomcatServletFilterUtil tomcatServletFilterUtil;

	@Override
	public void start(BundleContext context) throws Exception {

		initializeLoggerContext();

		initializeServices(context);

		doLog("Starting dotCMS OSGi filter plugin");

		if (tomcatServletFilterUtil == null) {
			tomcatServletFilterUtil = new TomcatServletFilterUtil();
		}

		/**
		 * Tweak below to test different scenarios
		 */
		tomcatServletFilterUtil.addServlet(HELLO_WORLD_SERVLET_NAME, new HelloWorldServlet(), "/hello");
 		tomcatServletFilterUtil.addServlet(GOODBYE_WORLD_SERVLET_NAME, new GoodbyeWorldServlet(), "/bye");

		tomcatServletFilterUtil.addFilter(HELLO_WORLD_FILTER_NAME, new HelloWorldFilter(), FilterOrder.FIRST, "/services/first-time-investors");
		tomcatServletFilterUtil.addFilter(GOODBYE_WORLD_FILTER_NAME, new GoodbyeWorldFilter(), FilterOrder.FIRST, "/services/retirees");

		doLog("Started dotCMS OSGi filter plugin");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		unregisterServices(context);

		doLog("Stopping dotCMS OSGi filter plugin");

		if (tomcatServletFilterUtil != null) {
			tomcatServletFilterUtil.removeServlet(HELLO_WORLD_SERVLET_NAME);
			tomcatServletFilterUtil.removeFilter(HELLO_WORLD_FILTER_NAME);

			tomcatServletFilterUtil.removeServlet(GOODBYE_WORLD_SERVLET_NAME);
			tomcatServletFilterUtil.removeFilter(GOODBYE_WORLD_FILTER_NAME);
		}

		doLog("Stopped dotCMS OSGi filter plugin");

		closeLoggerContext();
	}

	private void initializeLoggerContext() {
		//Initializing log4j...
        LoggerContext dotcmsLoggerContext = Log4jUtil.getLoggerContext();

        if (dotcmsLoggerContext != null) {

        	//Initialing the log4j context of this plugin based on the dotCMS logger context
        	pluginLoggerContext = (LoggerContext) LogManager.getContext(this.getClass().getClassLoader(),
        			false,
        			dotcmsLoggerContext,
        			dotcmsLoggerContext.getConfigLocation());
        }
	}

	private void closeLoggerContext() {
        if (pluginLoggerContext != null) {
        	Log4jUtil.shutdown(pluginLoggerContext);
        }
	}

	private void doLog(String message) {
		Logger.info(this, "**********************************");
		Logger.info(this, message);
		Logger.info(this, "**********************************");
	}

}
