package xyz.shuttle.filebox.frontend.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.shuttle.filebox.frontend.domain.User;
import xyz.shuttle.filebox.frontend.services.user.UserService;

@SpringView(name = "admin_view")
public class AdminView extends VerticalLayout implements View {
    private Grid<User> gridUsers = new Grid<>();

    @Autowired
    UserService userService;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        gridUsers.setSizeFull();
        gridUsers.setItems(userService.getUsers());
        gridUsers.getEditor().setEnabled(true);

        TextField textField = new TextField();

        gridUsers.addColumn(User::getUsername)
                .setCaption("UserName")
                .setEditorComponent(textField, User::setUsername);

        gridUsers.addColumn(User::getId).setCaption("Id");
        gridUsers.addColumn(User::isAccountNonExpired).setCaption("isAccountNonExpired");
        gridUsers.addColumn(User::isCredentialsNonExpired).setCaption("isCredentialsNonExpired");
        gridUsers.addColumn(User::isAccountNonLocked).setCaption("isAccountNonLocked");
        gridUsers.addColumn(User::isEnabled).setCaption("isEnabled");

        this.addComponent(gridUsers);
    }
}
