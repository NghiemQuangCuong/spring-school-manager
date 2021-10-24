package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.service.ImageService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;

/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Service
public class ImageServiceImpl implements ImageService {

    private static int randomNumberForClass = 0;
    private static int randomNumberForTeacher = 1;

    @Override
    public synchronized byte[] getRandomClassImageCover() throws IOException {
        String absolutePath = FileSystems.getDefault().getPath("")
                .normalize().toAbsolutePath().toString();
        if (randomNumberForClass > 9)
            randomNumberForClass = 0;
        String path = "/src/main/resources/static/img/class/class_image_cover_" + randomNumberForClass++ + ".png";
        File image = new File(absolutePath + path);
        return Files.readAllBytes(image.toPath());
    }

    @Override
    public synchronized byte[] getRandomTeacherImage() throws IOException {
        String absolutePath = FileSystems.getDefault().getPath("").normalize().toAbsolutePath().toString();
        if (randomNumberForTeacher > 295)
            randomNumberForTeacher = 1;
        String path = "/src/main/resources/static/img/teacher/img_teacher_" + numToString(randomNumberForTeacher++) + ".bmp";
        File image = new File(absolutePath + path);
        return Files.readAllBytes(image.toPath());
    }

    private String numToString(int num) {
        if (num < 10)
            return "00" + num;
        if (num < 100)
            return "0" + num;
        return String.valueOf(num);
    }
}
