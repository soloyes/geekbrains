package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.shuttle.filebox.frontend.services.AuthenticationService;
import xyz.shuttle.filebox.frontend.services.user.UserService;

@SpringView(name = "login")
public class LoginView extends VerticalLayout implements View {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    SpringNavigator navigator;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LoginForm loginForm = new LoginForm();
        loginForm.addLoginListener((LoginForm.LoginListener) loginEvent -> {
            if (authenticationService.login(
                    loginEvent.getLoginParameter("username"),
                    loginEvent.getLoginParameter("password"))) {
                navigator.navigateTo("main");
            }
        });
        this.addComponent(loginForm);
        this.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }
}
