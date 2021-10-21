package com.acmebank.accountmanager.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.acmebank.accountmanager.dto.UserData;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    @NotNull
    private String username;

    public UserData toData() {
        UserData userData = new UserData();

        userData.setId(id);
        userData.setUsername(username);

        return userData;
    }

    public User fromData(UserData userData) {
        User user = new User();

        user.setId(userData.getId());
        user.setUsername(userData.getUsername());

        return user;
    }
}