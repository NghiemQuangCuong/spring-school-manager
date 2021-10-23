package edu.cuongnghiem.springschoolmanager.exception;

/**
 * Created by cuongnghiem on 23/10/2021
 **/

public class BadRequestException extends RuntimeException{
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
