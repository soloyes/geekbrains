package com.geekbrains.geekmarket.entities;

import com.geekbrains.geekmarket.validation.FieldMatch;
import com.geekbrains.geekmarket.validation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
public class SystemUser {
    @NotNull(message = "not null check")
    @Size(min = 3, message = "username length must be greater than 2 symbols")
//    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 letters/digits")
    private String userName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String email;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String phone;

    public SystemUser() {
    }
}
