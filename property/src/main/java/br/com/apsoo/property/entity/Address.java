package br.com.apsoo.property.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private int id;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
    private String coordinates;
    private String number;
    private String complement;

}
