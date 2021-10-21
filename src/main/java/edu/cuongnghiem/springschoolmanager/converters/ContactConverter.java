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
        if (command == null)
            return null;
        return Contact.builder()
                .phone1(command.getPhone1())
                .phone2(command.getPhone2())
                .address(command.getAddress())
                .city(command.getCity())
                .build();
    }

    @Override
    public ContactCommand entityToCommand(Contact entity) {
        if (entity == null)
            return null;
        return ContactCommand.builder()
                .phone1(entity.getPhone1())
                .phone2(entity.getPhone2())
                .address(entity.getAddress())
                .city(entity.getCity())
                .build();
    }
}
