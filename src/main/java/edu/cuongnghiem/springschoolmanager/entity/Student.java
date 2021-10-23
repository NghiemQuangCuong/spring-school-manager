package edu.cuongnghiem.springschoolmanager.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 20/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student extends Person{

    @Builder
    public Student(Long id, String firstName, String lastName, ClassRoom classRoom, Contact contact, Set<Mark> marks) {
        super(id, firstName, lastName);
        this.classRoom = classRoom;
        this.contact = contact;
        if (marks != null)
            this.marks = marks;
    }

    @ManyToOne
    @JoinColumn(name = "class_room_id")
    private ClassRoom classRoom;

    @Embedded
    private Contact contact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private Set<Mark> marks = new HashSet<>();

    @Lob
    private byte[] image;
}
