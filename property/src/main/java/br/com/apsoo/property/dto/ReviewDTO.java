package br.com.apsoo.property.dto;

import br.com.apsoo.property.model.ReviewModel;

public record ReviewDTO(Long id, Integer score, String comment, ReservationDTO reservation) {
    public ReviewDTO(ReviewModel reviewModel) {
        this(reviewModel.getId(), reviewModel.getScore(), reviewModel.getComment(),
                new ReservationDTO(reviewModel.getReservation()));
    }
}
