package br.com.apsoo.property.dto;

import br.com.apsoo.property.model.ReservationModel;

import java.time.LocalDateTime;

public record ReservationDTO(Long id, LocalDateTime startDate, LocalDateTime endDate, double totalValue,
                             int daysToGiveUp, String state, long userId, PropertyDTO property,
                             TransactionDTO transactionDTO) {

    public ReservationDTO(ReservationModel reservationModel) {
        this(reservationModel.getId(), reservationModel.getStartDate(), reservationModel.getEndDate(),
                reservationModel.getTotalValue(), reservationModel.getDaysToGiveUp(), reservationModel.getState(),
                reservationModel.getUserId(), new PropertyDTO(reservationModel.getProperty()), null);
    }

    public ReservationDTO(ReservationModel reservationModel, TransactionDTO transactionDTO) {
        this(reservationModel.getId(), reservationModel.getStartDate(), reservationModel.getEndDate(),
                reservationModel.getTotalValue(), reservationModel.getDaysToGiveUp(), reservationModel.getState(),
                reservationModel.getUserId(), new PropertyDTO(reservationModel.getProperty()), transactionDTO);
    }
}
