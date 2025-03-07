package br.com.apsoo.property.dto;

public record CreateReviewDTO (Long id, Integer score, String comment, Long reservationId) {

}
