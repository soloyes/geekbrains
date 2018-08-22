package xyz.solovev.enterprise.entity;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Data
public abstract class AbstractEntity implements MyEntity {
    @Id
    @NotNull
    private String id = UUID.randomUUID().toString();
}
