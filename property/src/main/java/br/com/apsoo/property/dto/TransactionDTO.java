package br.com.apsoo.property.dto;

public record TransactionDTO(Long id, Long userId, Long reservationId, Double value, String method, String paymentUrl) {
}
