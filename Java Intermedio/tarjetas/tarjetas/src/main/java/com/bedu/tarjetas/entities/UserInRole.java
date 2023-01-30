package com.bedu.tarjetas.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_in_role")
public class UserInRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

}
