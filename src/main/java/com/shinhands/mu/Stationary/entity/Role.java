package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Role")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue
    @Column(name="id",nullable = false)
    private Long id;
    @Column(name="name_role",length = 50)
    private String nameRole;
    @Column(name = "deleted")
    private Long deleted;


//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    private List<RoleFuntion> roleFuntionList;
//
//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
//    private List<UserRole> userRoleList;
}
