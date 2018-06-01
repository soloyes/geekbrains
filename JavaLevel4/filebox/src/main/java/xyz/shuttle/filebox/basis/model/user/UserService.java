package xyz.shuttle.filebox.basis.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.shuttle.filebox.basis.domain.Role;
import xyz.shuttle.filebox.basis.domain.User;
import xyz.shuttle.filebox.basis.dao.UserDao;

import lombok.NonNull;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    //Default admin init.
    @PostConstruct
    private void init(){
        save("admin", "admin", Role.ADMIN);
    }
    //

    public void save(String userName, String password, Role role) {
        if (!userDao.findByUserName(userName).isPresent()) {
            userDao.save(User.builder()
                    .username(userName)
                    .password(new BCryptPasswordEncoder().encode(password))
                    .authorities(new LinkedList<>(Collections.singletonList(role)))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true).build());
        }
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        return userDao.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("User " + username + " was not found!"));
    }

    public List<User> getUsers(){
        return userDao.findAllUsers();
    }

    public void updateUser(String username, boolean enabled){
        userDao.updateUser(username, enabled);
    }
}
