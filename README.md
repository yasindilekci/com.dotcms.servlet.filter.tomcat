## com.dotcms.servlet.filter.tomcat

This is an OSGI plugin that can dynamically load and unload servlets and filters from a dotCMS Tomcat installation. Filters can be mapped to the ROOT, e.g. `/*` and be run first or last in the filter chain.

This is not warrented and has not been tested under load or in any production environment and there could be issues with classloaders, memory leaks etc. I wanted to share this as someone might find it useful.

### IMPORTANT!

Add the following imports to Exported Packages in dotCMS (tested in dotCMS 4):

org.apache.catalina,
org.apache.catalina.core,
org.apache.tomcat.util.descriptor.web,