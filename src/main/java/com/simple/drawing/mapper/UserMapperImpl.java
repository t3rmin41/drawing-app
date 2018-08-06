package com.simple.drawing.mapper;

import java.util.List;
import java.util.Set;
import com.simple.drawing.domain.RoleBean;
import com.simple.drawing.domain.UserBean;
import com.simple.drawing.enums.UserType;
import com.simple.drawing.jpa.RoleJpa;

public class UserMapperImpl implements UserMapper {

  @Override
  public UserBean getUserBeanByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean getUserBeanByEmailAndType(String email, UserType type) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean getUserBeanByEmailAndPassword(String email, String password) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserBean convertUserToBeanByUserId(Long id) {
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
  public List<RoleBean> convertUserRolesToRoleBeans(Set<RoleJpa> roles) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addRoles(Long userId, Set<String> roles) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void removeRoles(Long userId, Set<String> roles) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public Set<String> getRolesByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
    // TODO Auto-generated method stub
    return null;
  }

}
