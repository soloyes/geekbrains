package xyz.shuttle.filebox.basis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_share")
public class UserShare {
    @Id
    private ObjectId id;
    private String userFrom;
    private String userTo;
    private String filename;
}