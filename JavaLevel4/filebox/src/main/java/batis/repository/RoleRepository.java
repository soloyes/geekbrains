package batis.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mybatis.entity.Role;

import java.util.List;

public interface RoleRepository {
    @Select("SELECT * FROM roles")
    List<Role> findAll();

    @Select("SELECT * FROM roles WHERE id =#{id}")
    Role findById(Long id);

    @Insert("INSERT INTO roles (id, name) VALUES (#{id}, #{Name})")
    void insert(Role role);

    @Update("UPDATE roles SET name = #{Name} WHERE id = #{id}")
    void update(Role role);

    @Delete("DELETE FROM roles WHERE id = #{id}")
    void deleteById(Role user);
}
