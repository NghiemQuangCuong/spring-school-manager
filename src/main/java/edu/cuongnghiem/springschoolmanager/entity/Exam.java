package edu.cuongnghiem.springschoolmanager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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

    @Column(name = "date")
    private LocalDate date;

    @OneToOne
    @JoinColumn(name = "school_year_id")
    private SchoolYear schoolYear;

    @Enumerated(value = EnumType.ORDINAL)
    private ExamType examType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exam")
    private Set<Mark> marks = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}