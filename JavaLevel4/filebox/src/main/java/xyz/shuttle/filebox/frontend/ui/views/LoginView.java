package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import xyz.shuttle.filebox.frontend.services.AuthenticationService;

@SpringView(name = "login_view")
public class LoginView extends VerticalLayout implements View {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    MyLoginForm loginForm;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        loginForm.addLoginListener((LoginForm.LoginListener) loginEvent -> {
            try {
                authenticationService.login(
                        loginEvent.getLoginParameter("username"),
                        loginEvent.getLoginParameter("password"));
            }
            catch (AuthenticationException e){
                loginForm.getLabel().setValue("Wrong user/password!");
            }

            boolean isAdmin = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getAuthorities()
                    .stream()
                    .anyMatch(
                            r -> r.getAuthority().equals("ADMIN")
                    );
            boolean isUser = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getAuthorities()
                    .stream()
                    .anyMatch(
                            r -> r.getAuthority().equals("USER")
                    );
            if (isAdmin)
                getUI().getPage().setLocation("/admin_view");
            if (isUser)
                getUI().getPage().setLocation("/user_view");

        });

        this.addComponent(loginForm);
        this.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }
}