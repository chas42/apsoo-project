package br.com.apsoo.property.service;

import br.com.apsoo.property.dto.PropertyDTO;

import java.util.List;

public interface PropertyService {

    PropertyDTO createProperty(PropertyDTO propertyDTO);
    PropertyDTO updateProperty(PropertyDTO propertyDTO);
    void deleteProperty(long id);
    PropertyDTO getProperty(long id);
    List<PropertyDTO> getProperties();
    List<PropertyDTO> getPropertiesByUserId(long userId);
    List<PropertyDTO> getRentalPropertiesByUserId(long userId);

}
