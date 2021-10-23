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

    private static int randomNumber = 0;

    @Override
    public synchronized byte[] getRandomClassImageCover() throws IOException {
        String absolutePath = FileSystems.getDefault().getPath("")
                .normalize().toAbsolutePath().toString();
        if (randomNumber > 9)
            randomNumber = 0;
        String path = "/src/main/resources/static/img/class/class_image_cover_" + randomNumber++ + ".png";
        File image = new File(absolutePath + path);
        return Files.readAllBytes(image.toPath());
    }
}
