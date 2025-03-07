package br.com.apsoo.property.dto;

public record CreateTransactionDTO(Long userId, Long reservationId, Double value, String method) {
}
