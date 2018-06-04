package xyz.shuttle.filebox.basis.dao;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import xyz.shuttle.filebox.basis.domain.UserShare;
import xyz.shuttle.filebox.basis.domain.UserShareField;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class ShareDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<UserShare> findShare(@NonNull String userFrom, @NonNull String userTo, @NonNull String filename) {
        return Optional.ofNullable(
                mongoTemplate.findOne(
                        query(
                                where(UserShareField.USER_FROM.field()).is(userFrom)
                                        .and(UserShareField.USER_TO.field()).is(userTo)
                                        .and(UserShareField.FILENAME.field()).is(filename))

                        , UserShare.class));
    }

    public void save(@NonNull UserShare userShare) {
        mongoTemplate.save(userShare);
    }

    public void delete(@NonNull String userFrom, @NonNull String userTo, @NonNull String filename) {
        mongoTemplate.remove(
                query(
                        where(UserShareField.USER_FROM.field()).is(userFrom)
                                .and(UserShareField.USER_TO.field()).is(userTo)
                                .and(UserShareField.FILENAME.field()).is(filename))

                , UserShare.class);
    }

    public void delete(@NonNull String userFrom, @NonNull String filename){
        mongoTemplate.remove(
                query(
                        where(UserShareField.USER_FROM.field()).is(userFrom)
                                .and(UserShareField.FILENAME.field()).is(filename))

                , UserShare.class);
    }
}