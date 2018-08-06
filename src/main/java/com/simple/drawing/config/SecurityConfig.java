package com.simple.drawing.config;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.CompositeFilter;
import com.simple.drawing.filter.JwtAuthFilter;
import com.simple.drawing.filter.JwtLoginFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Inject
  private DataSource dataSource;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/h2-console/**",
                               "/resources/**",
                               "/privacypolicy",
                               "/swagger-ui.html", //Swagger components
                               "/swagger-resources/**", //Swagger components
                               "/v2/**", //Swagger components
                               "/webjars/**", //Swagger components
                               //"/dist/app/*",
                               "/app/**",
                               "/",
                               "/favicon.ico",
                               //"/users/login/success",
                               //"/users/logout",
                               "/wrapper/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable();
      http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
      http
          .authorizeRequests()
          .antMatchers("/login*", "/signin/**", "/signup/**").permitAll()
          .anyRequest().authenticated()
        .and()
          .addFilterBefore(loginFilters(), UsernamePasswordAuthenticationFilter.class)
          .addFilterBefore(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
          ;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.jdbcAuthentication().dataSource(dataSource)
      .usersByUsernameQuery("SELECT email AS username, password, enabled FROM users WHERE email = ? AND type = 'APP'")
      .authoritiesByUsernameQuery("SELECT user_id, CONCAT('ROLE_',role) AS authority FROM roles WHERE user_id = (SELECT id FROM users WHERE email = ? AND type = 'APP')")
      .passwordEncoder(passwordEncoder());
  }
  
  private Filter loginFilters() throws Exception {
    
    CompositeFilter filter = new CompositeFilter();
    List<Filter> filters = new ArrayList<>();

    JwtLoginFilter jwtLoginFilter = new JwtLoginFilter("/users/login", authenticationManager());
    filters.add(jwtLoginFilter);

    filter.setFilters(filters);
    return filter;
  }

}
