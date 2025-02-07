package com.reksoft.module1rest.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {


  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
      ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(servlet, "/ws/*");
  }

  @Bean(name = "users")
  public DefaultWsdl11Definition wsdlUsersDefinition(XsdSchema usersSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("UsersPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://example.com/users");
    wsdl11Definition.setSchema(usersSchema);
    return wsdl11Definition;
  }

  @Bean(name = "tasks")
  public DefaultWsdl11Definition wsdlTasksDefinition(XsdSchema tasksSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("TasksPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://example.com/tasks");
    wsdl11Definition.setSchema(tasksSchema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema usersSchema() {
    return new SimpleXsdSchema(new ClassPathResource("users.xsd"));
  }

  @Bean
  public XsdSchema tasksSchema() {
    return new SimpleXsdSchema(new ClassPathResource("tasks.xsd"));
  }
}
