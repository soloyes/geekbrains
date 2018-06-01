package xyz.shuttle.filebox.basis.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserShares {
    @Id
    private ObjectId id;
    private String filename;
    private String fromUser;
    private String toUser;
}
