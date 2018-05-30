package xyz.shuttle.filebox.frontend.dao;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import xyz.shuttle.filebox.frontend.domain.UserFiles;
import xyz.shuttle.filebox.frontend.domain.UserFilesField;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Component
public class FileDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public Optional<UserFiles> findFileByName(@NonNull String username, @NonNull String filename) {
        return Optional.ofNullable(
                mongoTemplate.findOne(
                        query(
                                where(UserFilesField.USER_NAME.field()).is(username)
                                        .and(UserFilesField.FILE_NAME.field()).is(filename))

                        , UserFiles.class));
    }

    public List<UserFiles> findAllFiles(){
        return mongoTemplate.findAll(UserFiles.class);
    }

    public void updateFile(@NonNull String username, boolean enabled){
    }

    public void save(@NonNull UserFiles userFile) {
        mongoTemplate.save(userFile);
    }
}
