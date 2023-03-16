package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "role_function")
@SequenceGenerator(name= "ROLE_FUNCTION_SEQUENCE", sequenceName = "ROLE_FUNCTION_SEQ", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleFuntion {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ROLE_FUNCTION_SEQUENCE")
    @Column(name = "id",nullable = false)
    private Long id;
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "function_id")
    private Long functionId;
    @Column(name = "deleted")
    private Long deleted = 0L;

//    @ManyToOne
//    @JoinColumn(name = "role_id", nullable = false, referencedColumnName = "id")
//    private Role role;
//
//    @ManyToOne
//    @JoinColumn(name = "role_function", nullable = false, referencedColumnName = "function_id")
//    private Function function;

}
