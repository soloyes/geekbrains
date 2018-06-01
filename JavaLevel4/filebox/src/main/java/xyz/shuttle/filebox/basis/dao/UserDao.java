package xyz.shuttle.filebox.basis.dao;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import xyz.shuttle.filebox.basis.domain.User;
import xyz.shuttle.filebox.basis.domain.UserField;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class UserDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<User> findByUserName(@NonNull String username) {
        return Optional.ofNullable(
                mongoTemplate.findOne(
                        query(
                                where(UserField.USER_NAME.field()).is(username))

                        , User.class));
    }

    public List<User> findAllUsers(){
        return mongoTemplate.findAll(User.class);
    }

    public void updateUser(@NonNull String username, boolean enabled){
        mongoTemplate.updateFirst(
                query(
                        where(UserField.USER_NAME.field()).is(username)
                ),
                new Update().set("enabled", enabled),
                User.class
        );
    }

    public void save(@NonNull User user) {
        mongoTemplate.save(user);
    }
}
