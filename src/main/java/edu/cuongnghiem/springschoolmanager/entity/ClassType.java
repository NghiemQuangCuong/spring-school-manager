package edu.cuongnghiem.springschoolmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 20/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClassType extends BaseEntity{

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "classType")
    private Set<ClassRoom> classes = new HashSet<>();
}
