package xyz.shuttle.filebox.basis.model.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.basis.domain.Role;
import xyz.shuttle.filebox.basis.model.files.FSService;
import xyz.shuttle.filebox.basis.model.user.UserService;

import java.io.IOException;

@Service
public class RegistartionServiceImpl implements RegistrationService {

    @Autowired
    UserService userService;

    @Autowired
    FSService fsService;

    @Override
    public boolean register(String username, String password) {
        if (username.isEmpty()) return false;
        try {
            userService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            userService.save(username, password, Role.USER);
            try {
                fsService.initUserDirectory(username);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
