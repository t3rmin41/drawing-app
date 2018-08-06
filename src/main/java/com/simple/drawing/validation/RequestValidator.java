package com.simple.drawing.validation;

import java.util.List;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.simple.drawing.security.UserNotAllowedException;

public interface RequestValidator {

  boolean validateRequestAgainstUserRoles(UsernamePasswordAuthenticationToken token, List<String> allowedRoles, String path)
  throws UserNotAllowedException;
  
}
