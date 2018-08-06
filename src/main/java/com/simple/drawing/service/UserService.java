package com.simple.drawing.service;

import java.util.List;
import java.util.Set;
import com.simple.drawing.domain.UserBean;
import com.simple.drawing.enums.UserType;

public interface UserService {

  UserBean getUserByEmailAndPassword(String email, String password);
  
  UserBean getUserByEmail(String email);
  
  UserBean getUserByEmailAndType(String email, UserType type);
  
  UserBean getUserById(Long id);
  
  UserBean saveUser(UserBean bean);
  
  UserBean saveUserFromSocial(UserBean bean, UserType type);
  
  List<UserBean> getAllUsers();
  
  boolean deleteUserById(Long id);
  
  UserBean updateUser(UserBean bean);

  Set<String> getRolesByEmail(String email);
  
  
}
