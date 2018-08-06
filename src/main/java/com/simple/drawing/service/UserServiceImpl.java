package com.simple.drawing.service;

import java.util.List;
import java.util.Set;
import com.simple.drawing.domain.UserBean;
import com.simple.drawing.enums.UserType;

public class UserServiceImpl implements UserService {

  @Override
  public UserBean getUserByEmailAndPassword(String email, String password) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean getUserByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean getUserByEmailAndType(String email, UserType type) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean getUserById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean saveUser(UserBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean saveUserFromSocial(UserBean bean, UserType type) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<UserBean> getAllUsers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteUserById(Long id) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public UserBean updateUser(UserBean bean) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<String> getRolesByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

}
