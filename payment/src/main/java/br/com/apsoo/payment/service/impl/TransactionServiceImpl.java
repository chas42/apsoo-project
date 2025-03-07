package br.com.apsoo.payment.service.impl;

import br.com.apsoo.payment.dto.CreateTransactionDTO;
import br.com.apsoo.payment.dto.TransactionDTO;
import br.com.apsoo.payment.model.TransactionModel;
import br.com.apsoo.payment.repository.TransactionRepository;
import br.com.apsoo.payment.dto.PaymentDTO;
import br.com.apsoo.payment.service.PaymentService;
import br.com.apsoo.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final PaymentService paymentService;

    @Autowired
    TransactionServiceImpl(TransactionRepository transactionRepository, PaymentService paymentService) {
        this.transactionRepository = transactionRepository;
        this.paymentService = paymentService;
    }

    @Override
    public TransactionDTO create(CreateTransactionDTO createTransactionDTO) {

        PaymentDTO paymentDTO = paymentService.createPayment(new PaymentDTO(createTransactionDTO.value(),
                createTransactionDTO.method(), null));

        TransactionModel transactionModel = transactionRepository.save(new TransactionModel(createTransactionDTO,
                paymentDTO.url()));

        return new TransactionDTO(transactionModel, paymentDTO.url());
    }

    @Override
    public List<TransactionDTO> listAll() {
        return transactionRepository.findAll().stream().map(TransactionDTO::new).toList();
    }
}
