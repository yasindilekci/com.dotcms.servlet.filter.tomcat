
## com.dotcms.servlet.filter.tomcat

This is an OSGI plugin that can dynamically load and unload servlets and filters from a dotCMS Tomcat installation. Filters can be mapped to the ROOT, e.g. `/*` and be run first or last in the filter chain.

This is not warrented and has not been tested under load or in any production environment and there could be issues with classloaders, memory leaks etc. I wanted to share this as someone might find it useful.

### IMPORTANT
#### Update Gradle build file
The build.gradle files contains the dotCMS and Tomcat version numbers, make sure to update these. Also update the overridden classes. Force a redownload of dependencies afterwards.

#### Useful commands

##### Build plugin (execute in root folder)

    gradlew.bat jar

##### Force Gradle to redownload dependencies

    gradlew.bat build --refresh-dependencies

#### Required exported packages
Add the following imports to Exported Packages in dotCMS (tested in dotCMS 4.X):

    org.apache.catalina,
    org.apache.catalina.core,
    org.apache.tomcat.util.descriptor.web,
