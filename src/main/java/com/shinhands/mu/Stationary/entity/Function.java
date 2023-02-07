package com.shinhands.mu.Stationary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "function")
@SequenceGenerator(name= "NAME_SEQUENCE", sequenceName = "FUNCTION_SEQ", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Function {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="NAME_SEQUENCE")

    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name",length = 255)
    private String name;
    @Column(name = "display_name")
    private Long displayName;
    @Column(name = "deleted")
    private Long deleted;

//    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL)
//    private List<RoleFuntion> roleFuntionList;

}
