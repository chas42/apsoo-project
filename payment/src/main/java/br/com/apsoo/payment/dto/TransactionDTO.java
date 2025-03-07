package br.com.apsoo.payment.dto;

import br.com.apsoo.payment.model.TransactionModel;

public record TransactionDTO(Long id, Long userId, Long reservationId, Long value, String method, String paymentUrl) {

    public TransactionDTO(TransactionModel transactionModel, String paymentUrl) {
        this(transactionModel.getId(), transactionModel.getUserId(), transactionModel.getReservationId(),
                transactionModel.getCost(), transactionModel.getMethod(), paymentUrl);
    }

    public TransactionDTO(TransactionModel transactionModel) {
        this(transactionModel.getId(), transactionModel.getUserId(), transactionModel.getReservationId(),
                transactionModel.getCost(), transactionModel.getMethod(), transactionModel.getPaymentUrl());
    }
}
