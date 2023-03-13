package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_website")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "USER_WEBSITE_SEQ", allocationSize = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWebsite {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="user_name",length = 255,nullable = false)
    private String userName;
    @Column(name="address",length = 255,nullable = false)
    private String address;
    @Column(name="phone",nullable = false)
    private String phone;

    @Column(name="id_account")
    private Long idAccount;
    @Column(name = "deleted")
    private Long deleted = 0L;


//    @OneToMany(mappedBy = "userWebsite", cascade = CascadeType.ALL)
//    private List<RoleFuntion> roleFuntionList;
//
//    @OneToOne(mappedBy = "userWebsite")
//    private Account account;

}
