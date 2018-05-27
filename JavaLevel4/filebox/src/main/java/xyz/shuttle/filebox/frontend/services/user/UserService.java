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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    //Default init users.
    @PostConstruct
    public void init() {
        if (!userDao.findByUserName("admin").isPresent()) {
            userDao.save(User.builder()
                    .username("admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .authorities(new LinkedList<>(Collections.singletonList(Role.ADMIN)))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true).build());
        }
    }
    //

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userDao.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("User " + username + " was not found!"));
    }

    public List<User> getUsers(){
        return userDao.findAllUsers();
    }
}
