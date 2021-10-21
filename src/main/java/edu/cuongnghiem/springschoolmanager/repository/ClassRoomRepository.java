package edu.cuongnghiem.springschoolmanager.repository;

import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
}
