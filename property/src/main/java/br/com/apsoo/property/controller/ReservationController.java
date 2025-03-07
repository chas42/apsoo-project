package br.com.apsoo.property.controller;

import br.com.apsoo.property.dto.PropertyDTO;
import br.com.apsoo.property.dto.ReservationDTO;
import br.com.apsoo.property.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> create(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationService.create(reservationDTO));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<ReservationDTO>> findByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.findByUserId(id));
    }

}
