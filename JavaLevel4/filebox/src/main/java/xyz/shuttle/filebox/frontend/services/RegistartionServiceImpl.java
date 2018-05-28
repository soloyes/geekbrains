package xyz.shuttle.filebox.frontend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.frontend.domain.Role;
import xyz.shuttle.filebox.frontend.domain.User;
import xyz.shuttle.filebox.frontend.persistence.UserDao;

import java.util.Collections;
import java.util.LinkedList;

@Service
public class RegistartionServiceImpl implements RegistrationService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean register(String username, String password) {
        if (!userDao.findByUserName(username).isPresent()) {
            userDao.save(User.builder()
                    .username(username)
                    .password(new BCryptPasswordEncoder().encode(password))
                    .authorities(new LinkedList<>(Collections.singletonList(Role.USER)))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true).build());
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
