package edu.cuongnghiem.springschoolmanager.converters;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

public interface EntityCommandConverter<E, C> {
    E commandToEntity (C command);
    C entityToCommand (E entity);
}
