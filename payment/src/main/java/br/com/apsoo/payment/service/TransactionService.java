package br.com.apsoo.payment.service;

import br.com.apsoo.payment.dto.CreateTransactionDTO;
import br.com.apsoo.payment.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO create(CreateTransactionDTO transactionDTO);

    List<TransactionDTO> listAll();
}
