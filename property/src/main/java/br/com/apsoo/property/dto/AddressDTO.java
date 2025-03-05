package br.com.apsoo.property.dto;

import br.com.apsoo.property.model.AddressModel;

public record AddressDTO(String street, String neighborhood, String city, String state, String zipCode,
                         String coordinates, String number, String complement) {

    public AddressDTO(AddressModel address) {
        this(address.getStreet(), address.getNeighborhood(), address.getCity(), address.getState(),
                address.getZipCode(), address.getCoordinates(), address.getNumber(), address.getComplement());
    }
}
