package edu.cuongnghiem.springschoolmanager.exception;

/**
 * Created by cuongnghiem on 24/10/2021
 **/

public class CacheMissingException extends RuntimeException{
    public CacheMissingException() {
        super();
    }

    public CacheMissingException(String message) {
        super(message);
    }
}
