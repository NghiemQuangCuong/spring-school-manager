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
public class Exam extends BaseEntity{

    @Builder
    public Exam(Long id, SchoolYear schoolYear, ExamType examType, Set<Mark> marks, Subject subject) {
        super(id);
        this.schoolYear = schoolYear;
        this.examType = examType;
        if (marks != null)
            this.marks = marks;
        this.subject = subject;
    }

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