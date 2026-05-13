package com.app.dataentry.config;

import com.app.dataentry.handlers.LoginHandler;
import com.app.dataentry.handlers.LogoutHandler;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.PathPatternRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

  @Autowired private UserDetailsService userDetailsService;

  @Autowired private LogoutHandler logoutHandler;

  @Autowired private LoginHandler loginHandler;

  @Autowired private SessionRegistry sessionRegistry;

  @Autowired private BCryptPasswordEncoder bcryptPasswordEncoder;

  @Bean
  public AuthenticationManager
  customAuthenticationManager(AuthenticationConfiguration authConfig)
      throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
      throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(bcryptPasswordEncoder);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
        .authorizeHttpRequests(
            auth
            -> auth.requestMatchers("/js/**", "/css/**", "/img/**",
                                    "/webjars/**")
                   .permitAll()
                   .requestMatchers(HttpMethod.POST, "/smscode")
                   .permitAll()
                   .anyRequest()
                   .authenticated())
        .formLogin(form
                   -> form.loginPage("/login")
                          .defaultSuccessUrl("/")
                          .successHandler(loginHandler)
                          .failureUrl("/login?error=true")
                          .permitAll())
        .logout(logout
                -> logout.clearAuthentication(true)
                       .invalidateHttpSession(true)
                       .logoutSuccessHandler(logoutHandler)
                       .logoutRequestMatcher(
                           PathPatternRequestMatcher.withDefaults().matcher(
                               "/logout"))
                       .logoutSuccessUrl("/login")
                       .permitAll())
        .sessionManagement(session
                           -> session.maximumSessions(1)
                                  .maxSessionsPreventsLogin(true)
                                  .sessionRegistry(sessionRegistry))
        .userDetailsService(userDetailsService)
        .build();
  }
}
