package xyz.shuttle.filebox.basis.ui.components;

import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import xyz.shuttle.filebox.basis.model.registration.RegistrationService;

@org.springframework.stereotype.Component
@Scope(value = "prototype")
public class LoginFormComponent extends LoginForm {
    private Label label = new Label();

    @Autowired
    RegistrationService registrationService;

    @Override
    protected Component createContent(TextField userNameField, PasswordField passwordField, Button loginButton) {

        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button regBtn = new Button("Registration");

        horizontalLayout.addComponents(loginButton, regBtn);
        horizontalLayout.setComponentAlignment(loginButton, Alignment.BOTTOM_LEFT);
        horizontalLayout.setComponentAlignment(regBtn, Alignment.BOTTOM_RIGHT);

        verticalLayout.setMargin(true);
        verticalLayout.addComponents(userNameField, passwordField, horizontalLayout, label);
        verticalLayout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
        verticalLayout.setComponentAlignment(userNameField, Alignment.BOTTOM_CENTER);
        verticalLayout.setComponentAlignment(passwordField, Alignment.BOTTOM_CENTER);

        regBtn.addClickListener(clickEvent -> {
            if (registrationService.register(userNameField.getValue(), passwordField.getValue()))
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
