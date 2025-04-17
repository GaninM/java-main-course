package org.example.module2restful;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.example.module2restful.configuration.AppConfig;
import org.example.module2restful.configuration.WebConfig;
import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Application {

  public static void main(String[] args) {
    Server server = new Server(8080);

    WebAppContext context = new WebAppContext();
    context.setContextPath("/");
    context.setResourceBase("src/main/webapp");
    context.setConfigurations(new Configuration[0]);
    context.addServlet(DispatcherServlet.class, "/");

    server.setHandler(context);
    try {
      server.start();
      server.join();
    } catch (Exception e) {
      throw new RuntimeException("Some problem in server start process. Exception: " + e);
    }

  }

  public static class AppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
      ServletRegistration.Dynamic h2Servlet = container.addServlet("H2Console", new WebServlet());
      h2Servlet.setLoadOnStartup(2);
      h2Servlet.addMapping("/h2-console/*");
      h2Servlet.setInitParameter("webAllowOthers", "true");

      AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
      context.register(AppConfig.class, WebConfig.class);

      ServletRegistration.Dynamic dispatcher = container.addServlet(
          "dispatcher", new DispatcherServlet(context));
      dispatcher.setLoadOnStartup(1);
      dispatcher.addMapping("/");
    }
  }
}
