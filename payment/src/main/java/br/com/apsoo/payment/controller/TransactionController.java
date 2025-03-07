package br.com.apsoo.payment.controller;

import br.com.apsoo.payment.dto.CreateTransactionDTO;
import br.com.apsoo.payment.dto.TransactionDTO;
import br.com.apsoo.payment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionDTO> create(@RequestBody CreateTransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.create(transactionDTO));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> listALl(){
        return  ResponseEntity.ok(transactionService.listAll());
    }

}
