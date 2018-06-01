package xyz.shuttle.filebox.basis.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.shuttle.filebox.basis.domain.User;
import xyz.shuttle.filebox.basis.model.user.UserService;

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

        CheckBox box = new CheckBox();

        gridUsers.addColumn(User::getUsername).setCaption("UserName");
        gridUsers.addColumn(User::getId).setCaption("Id");
        gridUsers.addColumn(User::isAccountNonExpired).setCaption("isAccountNonExpired");
        gridUsers.addColumn(User::isCredentialsNonExpired).setCaption("isCredentialsNonExpired");
        gridUsers.addColumn(User::isAccountNonLocked).setCaption("isAccountNonLocked");
        gridUsers.addColumn(User::isEnabled)
                .setCaption("isEnabled")
                .setEditorComponent(box, User::setEnabled);

        gridUsers.getEditor().addSaveListener(editorSaveEvent -> {
            userService.updateUser(editorSaveEvent.getBean().getUsername(), editorSaveEvent.getBean().isEnabled());
            gridUsers.setItems(userService.getUsers());
        });

        this.addComponent(gridUsers);
    }
}
