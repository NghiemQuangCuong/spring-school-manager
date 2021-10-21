package edu.cuongnghiem.springschoolmanager.command;

import lombok.Builder;
import lombok.Data;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Data
@Builder
public class ContactCommand {
    private String phone1;
    private String phone2;
    private String address;
    private String city;
}
