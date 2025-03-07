package br.com.apsoo.property.service;

import br.com.apsoo.property.dto.CreateTransactionDTO;
import br.com.apsoo.property.dto.TransactionDTO;

public interface TransactionService {

    TransactionDTO create(CreateTransactionDTO transactionDTO);

}
