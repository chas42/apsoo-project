package br.com.apsoo.payment.dto;

public record CreateTransactionDTO(Long userId, Long reservationId, Long value, String method) {

}
