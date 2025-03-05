package br.com.apsoo.property.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private int id;
    private String name;
    private String type;
    private Address address;
    private double maxPrice;
    private double minPrice;
    private String description;
    private Date maxDate;
    private Date minDate;
    private int numberRooms;
    private int maxGuests;
    private int userId;

}
