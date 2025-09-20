package com.shelfwise.backend.modules.auth.model;


import com.shelfwise.backend.modules.fine.model.Fine;
import com.shelfwise.backend.modules.reservation.models.Reservation;
import com.shelfwise.backend.modules.transactions.models.Transactions;
import jakarta.persistence.*;
import jakarta.transaction.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="Users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


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

    @OneToMany(mappedBy="user")
    private List<Fine> fine=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Transactions> transaction=new ArrayList<>();

    @OneToMany(mappedBy="user")
    private List<Reservation> reservation=new ArrayList<>();
}
