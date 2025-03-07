package br.com.apsoo.property.service;

import br.com.apsoo.property.dto.ReservationDTO;
import br.com.apsoo.property.model.ReservationModel;

import java.util.List;

public interface ReservationService {

    ReservationDTO create(ReservationDTO reservationDTO);

    ReservationModel findById(Long id);

    List<ReservationDTO> findByUserId(Long id);

}
