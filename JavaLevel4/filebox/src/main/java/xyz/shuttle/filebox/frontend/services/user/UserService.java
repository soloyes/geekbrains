package xyz.shuttle.filebox.frontend.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.frontend.domain.Role;
import xyz.shuttle.filebox.frontend.domain.User;
import xyz.shuttle.filebox.frontend.persistence.UserDao;

import lombok.NonNull;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.LinkedList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    //Test
    @PostConstruct
    public void init() {
        if (!userDao.findByUserName("user").isPresent()) {
            userDao.save(User.builder()
                    .username("user")
                    .password("password")
                    .authorities(new LinkedList<>(Arrays.asList(Role.USER)))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true).build());
        } else {
            userDao.findByUserName("user").ifPresent(
                    user -> {
                        user.setPassword(new BCryptPasswordEncoder().encode("password"));
                        userDao.save(user);
                    });

        }
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userDao.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("User " + username + " was not found!"));
    }
}
