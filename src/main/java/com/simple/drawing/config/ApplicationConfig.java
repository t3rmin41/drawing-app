package com.simple.drawing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.simple.drawing.app.ApplicationContextProvider;

@Configuration
@ComponentScan(basePackages = {"com.simple.drawing.controller", "com.simple.social.service", 
    "com.simple.social.mapper", "com.simple.social.repository",
    "com.simple.social.http.filter", "com.simple.social.aspect",
    "com.simple.social.errorhandling"
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
