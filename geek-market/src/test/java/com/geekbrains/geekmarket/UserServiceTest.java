package com.geekbrains.geekmarket;

import com.geekbrains.geekmarket.entities.SystemUser;
import com.geekbrains.geekmarket.entities.User;
import com.geekbrains.geekmarket.repositories.UserRepository;
import com.geekbrains.geekmarket.services.MailService;
import com.geekbrains.geekmarket.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MailService mailService;

    @Test
    public void userServiceTest() {
        SystemUser systemUser = new SystemUser();

        boolean isCreated = userService.save(systemUser);

        Assert.assertTrue(isCreated);

        Mockito.verify(userService, Mockito.times(1)).save(systemUser);
        Mockito.verify(mailService, Mockito.times(1))
                .sendMail(ArgumentMatchers.eq(systemUser.getEmail()), ArgumentMatchers.eq("2"), ArgumentMatchers.anyString());
    }

    @Test
    public void addUserFailTest() {
        SystemUser systemUser = new SystemUser();
        systemUser.setUserName("Michael");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findOneByUserName("Michael");

        boolean isCreated = userService.save(systemUser);

        Assert.assertFalse(isCreated);

        Mockito.verify(userService, Mockito.times(0)).save(ArgumentMatchers.any(SystemUser.class));
    }
}
