package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_role")
@SequenceGenerator(name= "USER_ROLE_SEQUENCE", sequenceName = "ROLE_USER_SEQ", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_ROLE_SEQUENCE")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "id_account")
    private Long idAccount;

    @Column(name = "deleted")
    private Long deleted = 0L;
}
