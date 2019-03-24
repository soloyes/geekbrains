package model;

import bean.User;
import model.service.UserService;
import model.service.UserServiceImpl;

import java.util.LinkedList;
import java.util.List;

public class MainModel implements Model{

    private ModelData modelData = new ModelData();

    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {

        modelData.setUsers(getAllUsers());
        modelData.setDisplayDeletedUserList(false);
    }

    @Override
    public void loadDeletedUsers() {
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
        modelData.setDisplayDeletedUserList(true);
    }

    @Override
    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    @Override
    public void deleteUserById(long id) {
        userService.deleteUser(id);
        List<User> list = new LinkedList<>(getAllUsers());
        modelData.setUsers(list);
        modelData.setDisplayDeletedUserList(false);
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
        List<User> list = new LinkedList<>(getAllUsers());
        modelData.setUsers(list);
        modelData.setDisplayDeletedUserList(false);
    }

    private List<User> getAllUsers(){
        List<User> list = new LinkedList<>(userService.getUsersBetweenLevels(1, 100));
        return userService.filterOnlyActiveUsers(list);
    }
}
