package com.simple.drawing.repository;

import java.util.List;
import java.util.Set;
import com.simple.drawing.enums.UserType;
import com.simple.drawing.jpa.RoleJpa;
import com.simple.drawing.jpa.UserJpa;

public class UserRepositoryImpl implements UserRepository {

  @Override
  public UserJpa getUserByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserJpa getUserByEmailAndType(String email, UserType type) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserJpa getUserByEmailAndPassword(String username, String password) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserJpa getUserById(Long id) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public UserJpa saveUser(UserJpa jpa) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void assignRoles(Set<RoleJpa> roles) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeRoles(Set<RoleJpa> roles) {
    // TODO Auto-generated method stub

  }

  @Override
  public List<UserJpa> getAllUsers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean deleteUser(UserJpa jpa) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public UserJpa updateUser(UserJpa jpa, boolean isPasswordChanged) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<RoleJpa> getUserRolesByNames(UserJpa jpa, Set<String> rolesNames) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<RoleJpa> getUserRolesByEmail(String email) {
    // TODO Auto-generated method stub
    return null;
  }

}
