package edu.cuongnghiem.springschoolmanager.controller.exceptionAndError;

import edu.cuongnghiem.springschoolmanager.exception.BadRequestException;
import edu.cuongnghiem.springschoolmanager.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Created by cuongnghiem on 23/10/2021
 **/
@ControllerAdvice
public class ExceptionHandlerController {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView notFoundException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("/exception/404");
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({NumberFormatException.class, BadRequestException.class})
    public ModelAndView badRequestException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("/exception/400");
        return modelAndView;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IOException.class)
    public ModelAndView serverException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setViewName("/exception/500");
        return modelAndView;
    }
}
