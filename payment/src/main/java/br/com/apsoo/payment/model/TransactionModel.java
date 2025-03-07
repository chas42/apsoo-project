package br.com.apsoo.payment.model;

import br.com.apsoo.payment.dto.CreateTransactionDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long reservationId;
    private Long cost;
    private String state;
    private String method;
    private String paymentUrl;

    public TransactionModel(CreateTransactionDTO createTransactionDTO, String paymentUrl) {
        this.userId = createTransactionDTO.userId();
        this.reservationId = createTransactionDTO.reservationId();
        this.cost = createTransactionDTO.value();
        this.method = createTransactionDTO.method();
        this.paymentUrl = paymentUrl;
    }
}
