package br.com.apsoo.property.dto;

import java.time.LocalDateTime;

import br.com.apsoo.property.model.PropertyModel;

public record PropertyDTO(Long id, String name, String type, double maxPrice, double minPrice, String description,
                          LocalDateTime maxDate, LocalDateTime minDate, int numberRooms, int maxGuests, long ownerId,
                          AddressDTO address) {

    public PropertyDTO(PropertyModel propertyModel) {
        this(propertyModel.getId(), propertyModel.getName(), propertyModel.getType(), propertyModel.getMaxPrice(),
                propertyModel.getMinPrice(), propertyModel.getDescription(), propertyModel.getMaxDate(),
                propertyModel.getMinDate(), propertyModel.getNumberRooms(), propertyModel.getMaxGuests(),
                propertyModel.getOwnerId(), new AddressDTO(propertyModel.getAddress()));
    }
}
