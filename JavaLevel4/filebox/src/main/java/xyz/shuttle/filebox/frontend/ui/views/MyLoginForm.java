package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import xyz.shuttle.filebox.frontend.services.RegistrationService;

@org.springframework.stereotype.Component
@Scope("prototype")
public class MyLoginForm extends LoginForm {
    private Label label = new Label();

    @Autowired
    RegistrationService register;

    @Override
    protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button auth = new Button("Registration");

        horizontalLayout.addComponents(loginButton, auth);
        horizontalLayout.setComponentAlignment(loginButton, Alignment.BOTTOM_LEFT);
        horizontalLayout.setComponentAlignment(auth, Alignment.BOTTOM_RIGHT);

        verticalLayout.setMargin(true);
        verticalLayout.addComponents(userNameField, passwordField, horizontalLayout, label);
        verticalLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(userNameField, Alignment.BOTTOM_CENTER);
        verticalLayout.setComponentAlignment(passwordField, Alignment.BOTTOM_CENTER);

        auth.addClickListener(clickEvent -> {
            if (register.register(userNameField.getValue(), passwordField.getValue()))
                label.setValue("User " + userNameField.getValue() + " is registered!");
            else
                label.setValue("User " + userNameField.getValue() + " already existed!");
        });

        return verticalLayout;
    }

    public Label getLabel() {
        return label;
    }
}
