package edu.cuongnghiem.springschoolmanager.repository;

import edu.cuongnghiem.springschoolmanager.entity.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long> {
}
