package edu.cuongnghiem.springschoolmanager.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by cuongnghiem on 20/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SchoolYear extends BaseEntity{

    @Builder
    public SchoolYear(Long id, Integer firstYear, Integer secondYear) {
        super(id);
        this.firstYear = firstYear;
        this.secondYear = secondYear;
    }

    private Integer firstYear;
    private Integer secondYear;
}
