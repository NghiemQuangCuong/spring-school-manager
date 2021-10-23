package edu.cuongnghiem.springschoolmanager.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 20/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Teacher extends Person{

    @Builder
    public Teacher(Long id, String firstName, String lastName, Contact contact, Set<ClassRoom> classes) {
        super(id, firstName, lastName);
        this.contact = contact;
        if (classes != null)
            this.classes = classes;
    }

    @Embedded
    private Contact contact;

    @ManyToMany(mappedBy = "teachers")
    private Set<ClassRoom> classes = new HashSet<>();

    @Lob
    private byte[] image;
}
