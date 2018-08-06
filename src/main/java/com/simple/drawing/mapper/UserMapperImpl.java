package com.simple.drawing.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.simple.drawing.domain.RoleBean;
import com.simple.drawing.domain.UserBean;
import com.simple.drawing.enums.UserType;
import com.simple.drawing.errorhandling.ErrorField;
import com.simple.drawing.errorhandling.WrongBeanFormatException;
import com.simple.drawing.jpa.RoleJpa;
import com.simple.drawing.validation.BeanValidator;
import com.simple.drawing.enums.RoleType;
import com.simple.drawing.jpa.UserJpa;
import com.simple.drawing.repository.UserRepository;

@Component
public class UserMapperImpl implements UserMapper, BeanValidator {

  @Inject
  private UserRepository userRepo;

  @Inject
  private PasswordEncoder passwordEncoder;
  
  @Override
  public UserBean getUserBeanByEmail(String email) {
    return convertJpaToBean(userRepo.getUserByEmail(email));
  }

  @Override
  public UserBean getUserBeanByEmailAndType(String email, UserType type) {
    return convertJpaToBean(userRepo.getUserByEmailAndType(email, type));
  }
  
  @Override
  public UserBean getUserBeanByEmailAndPassword(String email, String password) {
    UserJpa jpa = userRepo.getUserByEmailAndPassword(email, password);
    if (null != jpa) {
        return convertJpaToBean(jpa);
    } else {
        return null;
    }
  }

  @Override
  public UserBean convertUserToBeanByUserId(Long id) {
    UserJpa jpa = userRepo.getUserById(id);
    if (null != jpa) {
        return convertJpaToBean(jpa);
    } else {
        return null;
    }
  }

  @Override
  public UserBean saveUser(UserBean bean) {
    UserJpa jpa = new UserJpa();
    validateBean(bean);
    setSimpleFieldsFromBean(jpa, bean);
    jpa.setEnabled(true);
    jpa.setType(UserType.APP);
    UserJpa created = userRepo.saveUser(jpa);
    Set<String> roleNames = new HashSet<String>();
    bean.getRoles().stream().forEach(r -> {
      roleNames.add(r.getCode());
    });
    Set<RoleJpa> roles = convertUserNewRoleStringToRoles(created, roleNames);
    userRepo.assignRoles(roles);
    return convertUserToBeanByUserId(created.getId());
  }

  @Override
  public UserBean saveUserFromSocial(UserBean bean, UserType type) {
    UserJpa jpa = new UserJpa();
    validateBean(bean);
    setSimpleFieldsFromBean(jpa, bean);
    jpa.setEnabled(true);
    jpa.setType(type);
    UserJpa created = userRepo.saveUser(jpa);
    Set<String> roleNames = new HashSet<String>();
    bean.getRoles().stream().forEach(r -> {
      roleNames.add(r.getCode());
    });
    Set<RoleJpa> roles = convertUserNewRoleStringToRoles(created, roleNames);
    userRepo.assignRoles(roles);
    return convertUserToBeanByUserId(created.getId());
  }
  
  @Override
  public List<UserBean> getAllUsers() {
    List<UserBean> beans = new ArrayList<UserBean>();
    for (UserJpa jpa : userRepo.getAllUsers()) {
        beans.add(convertJpaToBean(jpa));
    }
    return beans;
  }

  @Override
  public boolean deleteUserById(Long id) {
    UserJpa jpa = userRepo.getUserById(id);
    return userRepo.deleteUser(jpa);
  }

  @Override
  public UserBean updateUser(UserBean bean) {
    UserJpa jpa = userRepo.getUserById(new Long(bean.getId()));
    validateBean(bean);
    setSimpleFieldsFromBean(jpa, bean);
    UserBean oldBean = convertUserToBeanByUserId(new Long(bean.getId()));
    if (checkValidRoles(bean.getRolesAsStrings())) {
      Set<String> oldRoles = new HashSet<String>(oldBean.getRolesAsStrings());
      Set<String> newRoles = new HashSet<String>(bean.getRolesAsStrings());
      if (!getOldRolesDifference(oldRoles, newRoles).isEmpty()) {
        removeRoles(new Long(bean.getId()), getOldRolesDifference(oldRoles, newRoles));
      }
      if (!getNewRolesDifference(oldRoles, newRoles).isEmpty()) {
        addRoles(new Long(bean.getId()), getNewRolesDifference(oldRoles, newRoles));
      }
    }
    return convertJpaToBean(userRepo.updateUser(jpa, checkIfPasswordChanged(bean)));
  }

  @Override
  public List<RoleBean> convertUserRolesToRoleBeans(Set<RoleJpa> roles) {
    List<RoleBean> roleBeans = new LinkedList<RoleBean>();
    for (RoleJpa role : roles) {
      roleBeans.add(new RoleBean().setCode(role.getRole()).setTitle(RoleType.getRoleTitleByCode(role.getRole())));
    }
    return roleBeans;
  }

  @Override
  public void addRoles(Long userId, Set<String> roles) {
    UserJpa jpa = userRepo.getUserById(userId);
    userRepo.assignRoles(convertUserNewRoleStringToRoles(jpa, roles));
  }

  @Override
  public void removeRoles(Long userId, Set<String> roles) {
    UserJpa jpa = userRepo.getUserById(userId);
    userRepo.removeRoles(userRepo.getUserRolesByNames(jpa, roles));
  }

  @Override
  public Set<String> getRolesByEmail(String email) {
    Set<String> roles = new HashSet<String>();
    userRepo.getUserRolesByEmail(email).stream().forEach(r -> {
      roles.add(r.getRole());
    });
    return roles;
  }

  @Override
  public Set<String> getNewRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
    Set<String> commonRoles = new HashSet<String>();
    commonRoles.addAll(newRoles);
    Set<String> newRoleSetDiff = new HashSet<String>();
    newRoleSetDiff.addAll(newRoles);
    commonRoles.retainAll(oldRoles);
    newRoleSetDiff.removeAll(commonRoles);
    return newRoleSetDiff;
  }

  @Override
  public Set<String> getOldRolesDifference(Set<String> oldRoles, Set<String> newRoles) {
    Set<String> commonRoles = new HashSet<String>();
    commonRoles.addAll(oldRoles);
    Set<String> oldRoleSetDiff = new HashSet<String>();
    oldRoleSetDiff.addAll(oldRoles);
    commonRoles.retainAll(newRoles);
    oldRoleSetDiff.removeAll(commonRoles);
    return oldRoleSetDiff;
  }

  @Override
  public List<ErrorField> validateBean(Serializable bean) throws WrongBeanFormatException {
    List<ErrorField> errors = new LinkedList<ErrorField>();
    UserBean userBean = (UserBean) bean;
    if (userBean.getRoles().size() < 1) {
      errors.add(new ErrorField("roles", "User must have at least 1 valid role"));
    }
    if (userBean.getRoles().size() > 0 && !checkValidRoles(userBean.getRolesAsStrings())) {
      errors.add(new ErrorField("roles", "Wrong one or more roles"));
    }
    if (errors.size() > 0) {
      throw new WrongBeanFormatException("Wrong data entered", errors);
    }
    return errors;
  }
  
  private Set<RoleJpa> convertUserNewRoleStringToRoles(UserJpa jpa, Set<String> roleNames) {
    Set<RoleJpa> roles = new HashSet<RoleJpa>();
    for (String rolename : roleNames) {
        RoleJpa jpaRole = new RoleJpa();
        jpaRole.setUser(jpa);
        jpaRole.setRole(rolename);
        roles.add(jpaRole);
    }
    return roles;
}

  private UserBean convertJpaToBean(UserJpa jpa) {
    if (null != jpa) {
      return new UserBean()//.setPassword(jpa.getPassword())
          .setFirstName(jpa.getFirstName())
          .setLastName(jpa.getLastName())
          .setEmail(jpa.getEmail())
          .setId(null != jpa.getId() ? jpa.getId() : null)
          .setRoles(convertUserRolesToRoleBeans(jpa.getRoles()))
          .setEnabled(jpa.getEnabled())
          .setType(jpa.getType().getTitle());
    }
    return null;
  }

  private UserJpa setSimpleFieldsFromBean(UserJpa jpa, UserBean bean) {
    jpa.setEmail(bean.getEmail());
    if (null != bean.getPassword() && !bean.getPassword().isEmpty() && !passwordEncoder.encode(bean.getPassword()).equals(jpa.getPassword())) {
      jpa.setPassword(bean.getPassword());
    }
    jpa.setFirstName(bean.getFirstName());
    jpa.setLastName(bean.getLastName());
    return jpa;
  }

  private boolean checkValidRoles(List<String> roles) {
    if (null == roles || 0 == roles.size()) {
      return false;
    }
    for (String role : roles) {
      if (!Arrays.asList(RoleType.values()).contains(RoleType.getRoleTypeByCode(role))) {
        return false;
      }
    }
    return true;
  }

  private boolean checkIfPasswordChanged(UserBean bean) {
    return null != bean.getPassword();
  }

}
