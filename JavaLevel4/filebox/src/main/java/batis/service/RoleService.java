package mybatis.service;

import batis.repository.RoleRepository;

import batis.entity.Role;

import java.io.IOException;
import java.util.List;

public class RoleService extends AbstractService {
    private final RoleRepository roleRepository;

    public RoleService() throws IOException {
        roleRepository = sqlSession.getMapper(RoleRepository.class);
    }

    public List<Role> finalAll() {
        return roleRepository.findAll();
    }

    public List<Long> ids() {
        return roleRepository.ids();
    }

    public Role findById(Long id) {
        return roleRepository.findById(id);
    }

    public void update(Role user) {
        roleRepository.update(user);
    }

    public void insert(Role user) {
        roleRepository.insert(user);
    }
}

