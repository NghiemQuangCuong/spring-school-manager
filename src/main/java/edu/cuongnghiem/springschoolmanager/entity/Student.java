package edu.cuongnghiem.springschoolmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by cuongnghiem on 20/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student extends Person{

    @ManyToOne
    @JoinColumn(name = "class_room_id")
    private ClassRoom classRoom;

    @Embedded
    private Contact contact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Mark> marks;
}
