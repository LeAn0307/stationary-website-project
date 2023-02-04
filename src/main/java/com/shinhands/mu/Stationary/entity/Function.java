package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "function")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Function {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name",length = 255)
    private String name;
    @Column(name = "display_name")
    private Long displayName;

    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL)
    private List<RoleFuntion> roleFuntionList;

}
