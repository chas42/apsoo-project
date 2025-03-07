package br.com.apsoo.property.model;

import br.com.apsoo.property.dto.CreateReviewDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReviewModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int score;
    private String comment;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private ReservationModel reservation;

    public ReviewModel(CreateReviewDTO reviewDTO) {
        this.score = reviewDTO.score();
        this.comment = reviewDTO.comment();
    }
}
