package xyz.shuttle.filebox;

import lombok.NonNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import xyz.shuttle.filebox.basis.domain.UserFiles;
import xyz.shuttle.filebox.basis.domain.UserFilesField;
import xyz.shuttle.filebox.basis.domain.UserShare;
import xyz.shuttle.filebox.basis.domain.UserShareField;

import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileboxApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
        System.out.println(new BCryptPasswordEncoder().encode("admin"));
    }

    @Test
    public void findShare() {
        System.out.println(Optional.ofNullable(
                mongoTemplate.findOne(
                        query(
                                where(UserShareField.USER_FROM.field()).is("user")
                                        .and(UserShareField.USER_TO.field()).is("user")
                                        .and(UserShareField.FILENAME.field()).is("Task8.txt"))
                        , UserShare.class)).isPresent());
    }
}
