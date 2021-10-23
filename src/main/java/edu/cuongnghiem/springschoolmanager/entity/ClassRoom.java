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
public class ClassRoom extends BaseEntity{

    @Builder
    public ClassRoom(Long id, String name, ClassType classType, Set<Student> students, Set<Teacher> teachers) {
        super(id);
        this.name = name;
        this.classType = classType;
        if (students != null)
            this.students = students;
        if (teachers != null)
            this.teachers = teachers;
    }

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "class_type_id")
    private ClassType classType;

    @OneToMany(mappedBy = "classRoom")
    private Set<Student> students = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "classes_teachers",
            joinColumns = @JoinColumn(name = "class_room_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers = new HashSet<>();

    @Lob
    private byte[] image;
}
