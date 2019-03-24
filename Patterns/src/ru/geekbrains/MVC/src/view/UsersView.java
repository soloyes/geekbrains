package view;

import bean.User;
import controller.Controller;
import model.ModelData;

public class UsersView implements View {

    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        String s = modelData.isDisplayDeletedUserList() ? "deleted " : "";
        System.out.println("All " + s + "users:");
        for (User u: modelData.getUsers() ) {
            System.out.println("\t" + u);
        }
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }
}
