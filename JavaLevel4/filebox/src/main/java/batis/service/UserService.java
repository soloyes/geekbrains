package mybatis.service;

import batis.entity.User;
import batis.repository.UserRepository;

import java.io.IOException;
import java.util.List;

public class UserService extends AbstractService {
    private final UserRepository userRepository;

    public UserService() throws IOException {
        userRepository = sqlSession.getMapper(UserRepository.class);
    }

    public List<User> finalAll() {
        return userRepository.findAll();
    }

    public List<Long> ids() {
        return userRepository.ids();
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public void deletebyId(User user) {
        userRepository.deleteById(user);
    }
}
