package edu.cuongnghiem.springschoolmanager.converters;

import edu.cuongnghiem.springschoolmanager.command.ContactCommand;
import edu.cuongnghiem.springschoolmanager.entity.Contact;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 21/10/2021
 **/
@Component
public class ContactConverter implements EntityCommandConverter<Contact, ContactCommand> {
    @Override
    public Contact commandToEntity(ContactCommand command) {
        return null;
    }

    @Override
    public ContactCommand entityToCommand(Contact entity) {
        return null;
    }
}
