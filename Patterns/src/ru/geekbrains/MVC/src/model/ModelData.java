package model;

import bean.User;

import java.util.List;

public class ModelData {

    private boolean displayDeletedUserList;

    private List<User> users;

    private User activeUser;

    public List<User> getUsers() {
        return users;
    }

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser) {
        this.activeUser = activeUser;
    }

    public boolean isDisplayDeletedUserList() {
        return displayDeletedUserList;
    }

    public void setDisplayDeletedUserList(boolean displayDeletedUserList) {
        this.displayDeletedUserList = displayDeletedUserList;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
