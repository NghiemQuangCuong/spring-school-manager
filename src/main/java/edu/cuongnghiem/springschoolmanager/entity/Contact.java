package edu.cuongnghiem.springschoolmanager.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * Created by cuongnghiem on 20/10/2021
 **/

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Contact {

    @Builder
    public Contact(String phone1, String phone2, String address, String city) {
        this.phone1 = phone1;
        this.phone2 = phone2;
        this.address = address;
        this.city = city;
    }

    private String phone1;
    private String phone2;
    private String address;
    private String city;
}
