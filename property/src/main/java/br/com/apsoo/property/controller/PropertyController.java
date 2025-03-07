package br.com.apsoo.property.controller;

import br.com.apsoo.property.dto.PropertyDTO;
import br.com.apsoo.property.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    private final PropertyService propertyService;

    @Autowired
    public PropertyController(@RequestBody PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyDTO> createProperty(@RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.ok(propertyService.create(propertyDTO));
    }

    @PutMapping
    public ResponseEntity<PropertyDTO> updateProperty(PropertyDTO propertyDTO) {
        return ResponseEntity.ok(propertyService.update(propertyDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable  long id) {
        propertyService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDTO> getProperty(@PathVariable long id) {
        try {
            return ResponseEntity.ok(propertyService.getById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Property not found", e);
        }
    }

    @GetMapping
    public ResponseEntity<List<PropertyDTO>> getProperties() {
        return ResponseEntity.ok(propertyService.listAll());
    }

}
