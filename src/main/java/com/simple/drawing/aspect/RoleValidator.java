package com.simple.drawing.aspect;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.simple.drawing.service.RequestValidator;

@Aspect
@Component
@SuppressWarnings("unused")
public class RoleValidator implements RequestValidator {

  private List<String> allowedManageRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN", "ROLE_MANAGER"}));
  private List<String> allowedModifyUserRoles = new LinkedList<String>(Arrays.asList(new String[]{"ROLE_ADMIN"}));
  
}
