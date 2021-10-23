package edu.cuongnghiem.springschoolmanager.service.implement;

import edu.cuongnghiem.springschoolmanager.service.ImageService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Random;

/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public byte[] getRandomClassImageCover() throws IOException {
        String absolutePath = FileSystems.getDefault().getPath("")
                .normalize().toAbsolutePath().toString();
        String path = "/src/main/resources/static/img/class/class_image_cover_" + new Random().nextInt(10) + ".png";
        File image = new File(absolutePath + path);
        return Files.readAllBytes(image.toPath());
    }
}
