package batis.repository;

import batis.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserRepository {
    @Select("SELECT * FROM users")
    List<User> findAll();

    @Select("SELECT id FROM users")
    List<Long> ids();

    @Select("SELECT * FROM users WHERE id =#{id}")
    User findById(Long id);

    @Insert("INSERT INTO users (id, username, password) VALUES (#{id}, #{userName}, #{password})")
    void insert(User user);

    @Update("UPDATE users SET username = #{userName}, password = #{password} WHERE id = #{id}")
    void update(User role);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteById(User user);
}