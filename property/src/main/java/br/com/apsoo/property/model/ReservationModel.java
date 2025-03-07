package br.com.apsoo.property.model;

import br.com.apsoo.property.dto.ReservationDTO;
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
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalValue;
    private int daysToGiveUp;
    private String state;
    private long userId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private PropertyModel property;

    public ReservationModel(ReservationDTO reservationDTO) {
        this.startDate = reservationDTO.startDate();
        this.endDate = reservationDTO.endDate();
        this.totalValue = reservationDTO.totalValue();
        this.daysToGiveUp = reservationDTO.daysToGiveUp();
        this.state = reservationDTO.state();
        this.userId = reservationDTO.userId();
    }
}
