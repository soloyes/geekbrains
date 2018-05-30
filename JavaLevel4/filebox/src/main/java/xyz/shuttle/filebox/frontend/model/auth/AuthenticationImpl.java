package xyz.shuttle.filebox.frontend.model.auth;

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
        return authentication.isAuthenticated();
    }

    @Override
    public void login(String login, String password) throws AuthenticationException{
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
        Authentication authentication;
            authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }
}