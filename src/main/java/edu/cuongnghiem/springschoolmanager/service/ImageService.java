package edu.cuongnghiem.springschoolmanager.service;

import java.io.IOException;

/**
 * Created by cuongnghiem on 23/10/2021
 **/

public interface ImageService {
    byte[] getRandomClassImageCover() throws IOException;
    byte[] getRandomTeacherImage() throws IOException;
    byte[] getRandomStudentImage() throws IOException;
}
