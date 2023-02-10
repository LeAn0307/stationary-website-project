package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "account")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "ACCOUNT_SEQ", allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name="id",nullable = true)
    private Long id;
    @Column(name="email",length = 255,nullable = false)
    private String email;
    @Column(name="account_password",length = 255,nullable = false)
    private String accountPassword;

    @Column(name = "deleted")
    private Long deleted = 0L;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "idaccount", referencedColumnName = "id")
//    private UserWebsite userWebsite;
}
