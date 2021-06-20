package com.app.dataentry.handlers;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dataentry.model.User;
import com.app.dataentry.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class LoginHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
                
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String username = userDetails.getUsername().split(":")[0];

                User user = userService.getUserByName(username);

                user.setIsLoggedIn(true);
                user.setLastLoggedInAt(LocalDateTime.now());

                userService.saveUser(user);
            }

}
