package edu.cuongnghiem.springschoolmanager.controller;

import edu.cuongnghiem.springschoolmanager.entity.ClassRoom;
import edu.cuongnghiem.springschoolmanager.exception.NotFoundException;
import edu.cuongnghiem.springschoolmanager.service.ClassRoomService;
import edu.cuongnghiem.springschoolmanager.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by cuongnghiem on 23/10/2021
 **/
@Controller
@RequestMapping("/image")
public class ImageController {

    private final ClassRoomService classRoomService;
    private final ImageService imageService;

    public ImageController(ClassRoomService classRoomService, ImageService imageService) {
        this.classRoomService = classRoomService;
        this.imageService = imageService;
    }

    @GetMapping("/class/{id}")
    public void getClassCoverImage(@PathVariable String id,
                                   HttpServletResponse response) throws IOException {
        ClassRoom classRoom = classRoomService.getClassRoomById(Long.valueOf(id));
        if (classRoom == null)
            throw new NotFoundException("ClassRoom not found, id = " + id);
        byte[] image = classRoom.getImage();
        if (image == null) {
            // we set classroom image to random if it is null
            image = imageService.getRandomClassImageCover();
            classRoom.setImage(image);
            classRoomService.saveClassRoom(classRoom);
        }
        response.setContentType("image/*");
        InputStream is = new ByteArrayInputStream(image);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
        response.getOutputStream().close();
    }
}
