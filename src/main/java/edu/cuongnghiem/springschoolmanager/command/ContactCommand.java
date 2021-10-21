package edu.cuongnghiem.springschoolmanager.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
public class ContactCommand {
    private String phone1;
    private String phone2;
    private String address;
    private String city;
}
