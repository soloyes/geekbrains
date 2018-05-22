package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.shuttle.filebox.frontend.services.RegistrationService;

@org.springframework.stereotype.Component
public class MyLoginForm extends LoginForm {
    @Autowired
    RegistrationService register;

    @Override
    protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button auth = new Button("Registration");
        auth.addClickListener(clickEvent -> {
            if (register.register(userNameField.getValue(), passwordField.getValue()))
                Notification.show("User " + userNameField.getValue() + " is registered!");
            else
                Notification.show("User " + userNameField.getValue() + " already existed!");
        });
        verticalLayout.setMargin(true);
        horizontalLayout.addComponents(loginButton, auth);
        horizontalLayout.setComponentAlignment(loginButton, Alignment.BOTTOM_LEFT);
        horizontalLayout.setComponentAlignment(auth, Alignment.BOTTOM_RIGHT);
        verticalLayout.addComponents(userNameField, passwordField, horizontalLayout);
        verticalLayout.setComponentAlignment(userNameField, Alignment.BOTTOM_CENTER);
        verticalLayout.setComponentAlignment(passwordField, Alignment.BOTTOM_CENTER);
        return verticalLayout;
    }
}
