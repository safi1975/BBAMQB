package com.app.dataentry.handlers;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.dataentry.constants.Role;
import com.app.dataentry.model.User;
import com.app.dataentry.repositories.UserRepository;
import com.app.dataentry.services.SmsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SmsService smsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String username = userDetails.getUsername().split(":")[0];

        User user = userRepository.findByName(username);

        user.setIsLoggedIn(true);
        user.setLastLoggedInAt(LocalDateTime.now());

        if (userRepository.save(user) != null) {

            for (User u : userRepository.findAll()) {

                if (u.getRole().equals(Role.ADMIN)) {
                    smsService.sendOperatorLoggedInSMS(u.getMobileNo(), user.getName());
                }
            }

            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
