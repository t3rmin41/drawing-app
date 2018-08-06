package com.simple.drawing.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

import com.simple.drawing.config.ActiveMqConfig;
import com.simple.drawing.config.ApplicationConfig;
import com.simple.drawing.config.JpaConfig;
import com.simple.drawing.config.MvcConfig;
import com.simple.drawing.config.SecurityConfig;

@SpringBootApplication
@Import({ApplicationConfig.class, JpaConfig.class, MvcConfig.class, ActiveMqConfig.class, SecurityConfig.class})
public class Main { // extends SpringBootServletInitializer {

  private static Logger log = LoggerFactory.getLogger(Main.class);

//  //for traditional .war deployment need to extend SpringBootServletInitializer
//  @Override
//  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//      return application.sources(Main.class);
//  }
  
  public static void main(String[] args) {
    SpringApplication springApplication = new SpringApplication(Main.class);
    springApplication.setBannerMode(Banner.Mode.OFF);
    ApplicationContext context = springApplication.run(args);
    log.warn("Context : " + context.getId());
  }
  
}