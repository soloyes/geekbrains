package model;

import bean.User;
import model.service.UserServiceImpl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    private UserServiceImpl userService;

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> list = new LinkedList<>(Arrays.asList(
                new User("A", 1L, 1),
                new User("B", 2L, 1)));

        modelData.setUsers(list);
    }

    @Override
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw new UnsupportedOperationException();
    }
}
