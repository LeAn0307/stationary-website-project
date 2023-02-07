package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="email",length = 255,nullable = false)
    private String email;
    @Column(name="account_password",length = 255,nullable = false)
    private String accountPassword;

    @Column(name = "deleted")
    private Long deleted;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "idaccount", referencedColumnName = "id")
//    private UserWebsite userWebsite;
}
