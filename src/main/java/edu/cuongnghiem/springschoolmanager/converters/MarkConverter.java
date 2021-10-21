package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.MarkCommand;
import edu.cuongnghiem.springschoolmanager.entity.Mark;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class MarkConverter implements EntityCommandConverter<Mark, MarkCommand> {
    @Override
    public Mark commandToEntity(MarkCommand command) {
        return null;
    }

    @Override
    public MarkCommand entityToCommand(Mark entity) {
        return null;
    }
}
