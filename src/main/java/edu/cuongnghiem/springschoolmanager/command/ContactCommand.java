package edu.cuongnghiem.springschoolmanager.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Created by cuongnghiem on 21/10/2021
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactCommand {
    private String phone1;
    private String phone2;
    @NotBlank(message = "Address must not be blank")
    private String address;
    @NotBlank(message = "City must not be blank")
    private String city;
}
