package br.com.apsoo.property.model;

import br.com.apsoo.property.dto.PropertyDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PropertyModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private double maxPrice;
    private double minPrice;
    private String description;
    private LocalDateTime maxDate;
    private LocalDateTime minDate;
    private int numberRooms;
    private int maxGuests;
    private long ownerId;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressModel address;

    public PropertyModel(PropertyDTO propertyDTO) {
        this.name = propertyDTO.name();
        this.type = propertyDTO.type();
        this.maxPrice = propertyDTO.maxPrice();
        this.minPrice = propertyDTO.minPrice();
        this.description = propertyDTO.description();
        this.maxDate = propertyDTO.maxDate();
        this.minDate = propertyDTO.minDate();
        this.numberRooms = propertyDTO.numberRooms();
        this.maxGuests = propertyDTO.maxGuests();
        this.ownerId = propertyDTO.ownerId();
        this.address = new AddressModel(propertyDTO.address());
    }
}
