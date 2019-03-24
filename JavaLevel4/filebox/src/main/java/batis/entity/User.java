package batis.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import batis.entity.Role;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User {
    private Long id;
    private String userName;
    private String password;
    private List<Role> roles;
}
