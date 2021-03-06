package com.simple.drawing.jpa;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import com.simple.drawing.enums.UserType;

@Entity
@Table(name = "USERS",
       uniqueConstraints = @UniqueConstraint(columnNames={"EMAIL", "TYPE"})
)
public class UserJpa {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  @Column(name = "ID")
  private Long id;
  @Column(name = "FIRST_NAME")
  private String firstName;
  @Column(name = "LAST_NAME")
  private String lastName;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "PASSWORD")
  private String password;
  //@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="user") // for using custom userDetails implementation - need to fetch roles eagerly 
  @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="user")
  private Set<RoleJpa> roles = new HashSet<RoleJpa>();
  @Column(name = "ENABLED")
  private boolean enabled;

  @Enumerated(EnumType.STRING)
  @Column(name = "TYPE")
  private UserType type;

  public Long getId() {
      return id;
  }
  public void setId(Long id) {
      this.id = id;
  }
  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getPassword() {
      return password;
  }
  public void setPassword(String password) {
      this.password = password;
  }
  public Set<RoleJpa> getRoles() {
      return roles;
  }
  public void setRoles(Set<RoleJpa> roles) {
      this.roles = roles;
  }
  public boolean getEnabled() {
      return enabled;
  }
  public void setEnabled(boolean enabled) {
      this.enabled = enabled;
  }
  public UserType getType() {
    return type;
  }
  public void setType(UserType type) {
    this.type = type;
  }
  
}
