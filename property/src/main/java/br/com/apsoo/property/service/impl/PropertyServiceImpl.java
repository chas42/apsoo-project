package br.com.apsoo.property.service.impl;

import br.com.apsoo.property.dto.PropertyDTO;
import br.com.apsoo.property.dto.UserDTO;
import br.com.apsoo.property.entity.property.ApartmentPropertyCreator;
import br.com.apsoo.property.entity.property.HousePropertyCreator;
import br.com.apsoo.property.entity.property.Property;
import br.com.apsoo.property.entity.property.PropertyCreator;
import br.com.apsoo.property.model.AddressModel;
import br.com.apsoo.property.model.PropertyModel;
import br.com.apsoo.property.repository.PropertyRepository;
import br.com.apsoo.property.service.PropertyService;
import br.com.apsoo.property.service.UserService;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserService userService;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository,UserService userService) {
        this.propertyRepository = propertyRepository;
        this.userService = userService;
    }

    @Override
    public PropertyDTO create(PropertyDTO propertyDTO) {

        PropertyModel propertyModel = new PropertyModel(propertyDTO);

        UserDTO userDTO = userService.getById(propertyDTO.ownerId());

        if (userDTO.id() == null){
            throw new NotFoundException("User not found");
        }

        Property validProperty = null;
        switch (propertyModel.getType()){
            case "house":
                validProperty = new HousePropertyCreator().createProperty();
                validProperty.validateProperty(propertyModel);
                break;
            case "apartment":
                validProperty = new ApartmentPropertyCreator().createProperty();
                validProperty.validateProperty(propertyModel);
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }

        propertyModel = propertyRepository.save(propertyModel);

        return new PropertyDTO(propertyModel);
    }

    @Override
    public PropertyDTO update(PropertyDTO propertyDTO) {

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
        existingProperty.setOwnerId(propertyDTO.ownerId());
        existingProperty.setAddress(new AddressModel(propertyDTO.address()));

        existingProperty = propertyRepository.save(existingProperty);

        return new PropertyDTO(existingProperty);
    }

    @Override
    public void delete(long id) {

        PropertyModel existingProperty = propertyRepository.findById(id).orElseThrow(NotFoundException::new);
        // Verificar se não tem nenhum contrato pendente

        propertyRepository.delete(existingProperty);

    }

    @Override
    public PropertyDTO getById(long id) {
        PropertyModel existingProperty = propertyRepository.findById(id).orElseThrow(NotFoundException::new);

        return new PropertyDTO(existingProperty);
    }

    @Override
    public PropertyModel getByIdModel(long id) {
        return propertyRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<PropertyDTO> listAll() {

        List<PropertyModel> properties =  propertyRepository.findAll();

        return  properties.stream().map(PropertyDTO::new).toList();
    }

    @Override
    public List<PropertyDTO> getByUserId(long userId) {
        return List.of();
    }

    @Override
    public List<PropertyDTO> getRentalPropertiesByUserId(long userId) {
        return List.of();
    }

}
