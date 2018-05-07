package xyz.shuttle.filebox.frontend.services;

import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationImpl implements AuthenticationService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getCredentials());
        return authentication.isAuthenticated();
    }

    @Override
    public boolean login(String login, String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (AuthenticationException e) {
            Notification.show("Wrong login/password!", Notification.Type.ERROR_MESSAGE);
            return false;
        }
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}