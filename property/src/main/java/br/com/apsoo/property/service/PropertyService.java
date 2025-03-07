package br.com.apsoo.property.service;

import br.com.apsoo.property.dto.PropertyDTO;
import br.com.apsoo.property.model.PropertyModel;

import java.util.List;

public interface PropertyService {

    PropertyDTO create(PropertyDTO propertyDTO);
    PropertyDTO update(PropertyDTO propertyDTO);
    void delete(long id);
    PropertyDTO getById(long id);
    PropertyModel getByIdModel(long id);
    List<PropertyDTO> listAll();
    List<PropertyDTO> getByUserId(long userId);
    List<PropertyDTO> getRentalPropertiesByUserId(long userId);

}
