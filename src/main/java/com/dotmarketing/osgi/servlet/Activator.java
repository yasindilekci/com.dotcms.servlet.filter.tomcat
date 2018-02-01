package com.dotmarketing.osgi.servlet;

import org.osgi.framework.BundleContext;

import com.dotcms.repackage.org.apache.logging.log4j.LogManager;
import com.dotcms.repackage.org.apache.logging.log4j.core.LoggerContext;
import com.dotmarketing.loggers.Log4jUtil;
import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

public class Activator extends GenericBundleActivator {

	private LoggerContext pluginLoggerContext;

	private static final String FILTER_NAME = "helloWorldFilter";
	private static final String SERVLET_NAME = "helloWorldServlet";

	@Override
	public void start(BundleContext context) throws Exception {
		Logger.info(this, "Starting dotCMS OSGi filter plugin");

		initializeServices(context);

		initializeLoggerContext();

		new TomcatServletFilterUtil().addServlet(SERVLET_NAME, new HelloWorldServlet(), "/helloWorld");

		new TomcatServletFilterUtil().addFilter(FILTER_NAME, new HelloWorldFilter(), FilterOrder.FIRST, "*", "/helloWorld");

		Logger.info(this, "Started dotCMS OSGi filter plugin");
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		unregisterServices(context);

		closeLoggerContext();

		Logger.info(this, "Stopping dotCMS OSGi filter plugin");

		new TomcatServletFilterUtil().removeServlet(SERVLET_NAME);
		new TomcatServletFilterUtil().removeFilter(FILTER_NAME);

		Logger.info(this, "Stopped dotCMS OSGi filter plugin");
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

}
