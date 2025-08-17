package com.shelfwise.backend.modules.auth.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;


    @Column(nullable = false)
    private String userName;


    @Column(nullable = false)
    private String userEmail;


    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status=Status.ACTIVE;

    public String toString(){
        return userId+" "+userName+" "+userEmail+" "+password+" "+role+" "+status;
    }

}
