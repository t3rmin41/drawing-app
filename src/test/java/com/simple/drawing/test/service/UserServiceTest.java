package com.simple.drawing.test.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import com.simple.drawing.config.ApplicationConfig;
import com.simple.drawing.domain.UserBean;
import com.simple.drawing.service.UserService;
import com.simple.drawing.test.config.ServiceTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {ApplicationConfig.class, ServiceTestConfig.class}, loader = AnnotationConfigContextLoader.class)
public class UserServiceTest {

  @Inject
  private UserService users;
  
  @Test
  public void checkUserById() {
    UserBean userBean = users.getUserById(1L);
    assertEquals("1", userBean.getId().toString());
  }

}
