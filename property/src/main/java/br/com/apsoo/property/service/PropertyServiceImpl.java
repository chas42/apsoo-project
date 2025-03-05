package br.com.apsoo.property.service;

import br.com.apsoo.property.dto.PropertyDTO;
import br.com.apsoo.property.model.AddressModel;
import br.com.apsoo.property.model.PropertyModel;
import br.com.apsoo.property.repository.PropertyRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {

        PropertyModel propertyModel = new PropertyModel(propertyDTO);

        propertyModel = propertyRepository.save(propertyModel);

        return new PropertyDTO(propertyModel);
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO) {

        PropertyModel existingProperty = propertyRepository.findById(propertyDTO.id()).orElseThrow(NotFoundException::new);

        existingProperty.setName(propertyDTO.name());
        existingProperty.setType(propertyDTO.type());
        existingProperty.setMaxPrice(propertyDTO.maxPrice());
        existingProperty.setMinPrice(propertyDTO.minPrice());
        existingProperty.setDescription(propertyDTO.description());
        existingProperty.setMaxDate(propertyDTO.maxDate());
        existingProperty.setMinDate(propertyDTO.minDate());
        existingProperty.setNumberRooms(propertyDTO.numberRooms());
        existingProperty.setMaxGuests(propertyDTO.maxGuests());
        existingProperty.setUserId(propertyDTO.userId());
        existingProperty.setAddress(new AddressModel(propertyDTO.address()));

        existingProperty = propertyRepository.save(existingProperty);

        return new PropertyDTO(existingProperty);
    }

    @Override
    public void deleteProperty(long id) {

        PropertyModel existingProperty = propertyRepository.findById(id).orElseThrow(NotFoundException::new);
        // Verificar se não tem nenhum contrato pendente

        propertyRepository.delete(existingProperty);

    }

    @Override
    public PropertyDTO getProperty(long id) {
        PropertyModel existingProperty = propertyRepository.findById(id).orElseThrow(NotFoundException::new);

        return new PropertyDTO(existingProperty);
    }

    @Override
    public List<PropertyDTO> getProperties() {

        List<PropertyModel> properties =  propertyRepository.findAll();

        return  properties.stream().map(PropertyDTO::new).toList();
    }

    @Override
    public List<PropertyDTO> getPropertiesByUserId(long userId) {
        return List.of();
    }

    @Override
    public List<PropertyDTO> getRentalPropertiesByUserId(long userId) {
        return List.of();
    }

}
