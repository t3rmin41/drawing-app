package com.simple.drawing.repository;

import java.util.List;
import java.util.Set;
import com.simple.drawing.enums.UserType;
import com.simple.drawing.jpa.RoleJpa;
import com.simple.drawing.jpa.UserJpa;

public interface UserRepository {

  UserJpa getUserByEmail(String email);
  
  UserJpa getUserByEmailAndType(String email, UserType type);
  
  UserJpa getUserByEmailAndPassword(String username, String password);
  
  UserJpa getUserById(Long id);
  
  UserJpa saveUser(UserJpa jpa);
  
  void assignRoles(Set<RoleJpa> roles);
  
  void removeRoles(Set<RoleJpa> roles);
  
  List<UserJpa> getAllUsers();
  
  boolean deleteUser(UserJpa jpa);
  
  UserJpa updateUser(UserJpa jpa, boolean isPasswordChanged);
  
  Set<RoleJpa> getUserRolesByNames(UserJpa jpa, Set<String> rolesNames);
  
  Set<RoleJpa> getUserRolesByEmail(String email);
  
}
