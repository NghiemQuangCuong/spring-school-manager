package edu.cuongnghiem.springschoolmanager.exception;

/**
 * Created by cuongnghiem on 23/10/2021
 **/

public class NotFoundException extends RuntimeException{
    public NotFoundException(){
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
