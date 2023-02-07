package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "ROLE_USER_SEQ", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "id_role")
    private Long idRole;
    @Column(name = "iduser")
    private Long idUser;
    @Column(name = "deleted")
    private Long deleted;

//    @ManyToOne
//    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
//    private Role role;
//
//    @ManyToOne
//    @JoinColumn(name = "user_role", nullable = false, referencedColumnName = "iduser")
//    private UserWebsite userWebsite;
}
