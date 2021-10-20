package edu.cuongnghiem.springschoolmanager.entity;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@Entity
public class Exam extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "school_year_id")
    private SchoolYear schoolYear;

    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exam")
    private Set<Mark> marks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}