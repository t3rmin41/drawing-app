package com.simple.drawing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.simple.drawing.app.ApplicationContextProvider;

@Configuration
@ComponentScan(basePackages = {"com.simple.drawing.controller", "com.simple.drawing.service", 
    "com.simple.drawing.mapper", "com.simple.drawing.repository",
    "com.simple.drawing.filter", "com.simple.drawing.aspect",
    "com.simple.drawing.errorhandling"
})
public class ApplicationConfig {

  @Bean
  public ApplicationContextProvider applicationContextProvider() {
    return new ApplicationContextProvider();
  }
  
//  @Bean
//  public ServletRegistrationBean h2servletRegistration(){
//      ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
//      registrationBean.addUrlMappings("/h2-console/*");
//      return registrationBean;
//  }
  
}
