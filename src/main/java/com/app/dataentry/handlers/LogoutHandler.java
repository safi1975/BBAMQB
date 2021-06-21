package com.app.dataentry.handlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dataentry.model.User;
import com.app.dataentry.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername().split(":")[0];

        User user = userService.getUserByName(username);

        user.setIsLoggedIn(false);

        for (SessionInformation sessionInformation : sessionRegistry.getAllSessions(authentication.getPrincipal(), false)) {
            sessionInformation.expireNow();
        }

        if (userService.saveUser(user) != null) {
            super.onLogoutSuccess(request, response, authentication);
        }

    }

}
