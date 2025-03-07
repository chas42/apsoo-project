package br.com.apsoo.payment.service;

import br.com.apsoo.payment.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO createPayment(PaymentDTO paymentDto);

}
