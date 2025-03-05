package br.com.apsoo.property.model;

import br.com.apsoo.property.dto.AddressDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
    private String coordinates;
    private String number;
    private String complement;

    public AddressModel(AddressDTO address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.city = address.city();
        this.state = address.state();
        this.zipCode = address.zipCode();
        this.coordinates = address.coordinates();
        this.number = address.number();
        this.complement = address.complement();
    }
}
