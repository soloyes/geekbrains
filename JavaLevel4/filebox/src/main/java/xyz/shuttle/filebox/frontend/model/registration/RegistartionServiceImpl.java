package xyz.shuttle.filebox.frontend.model.registration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.frontend.domain.Role;
import xyz.shuttle.filebox.frontend.model.user.UserService;

@Service
public class RegistartionServiceImpl implements RegistrationService {

    @Autowired
    UserService userService;

    @Override
    public boolean register(String userName, String password) {
        try{
            userService.loadUserByUsername(userName);
        } catch (UsernameNotFoundException e) {
            userService.save(userName, password, Role.USER);
            return true;
        }
        return false;
    }

    @Override
    public boolean isRegistered() {
        return false;
    }

    @Override
    public boolean unRegister() {
        return false;
    }
}
