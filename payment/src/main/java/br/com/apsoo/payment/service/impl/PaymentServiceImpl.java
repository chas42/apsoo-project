package br.com.apsoo.payment.service.impl;

import br.com.apsoo.payment.service.PaymentDTO;
import br.com.apsoo.payment.service.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDto) {

        // TODO criar conexao com servico de pagamento esterno

        return new PaymentDTO(paymentDto.value(), paymentDto.method(), "http://localhost:8080/payment");
    }

}
