package br.com.apsoo.property.service.impl;

import br.com.apsoo.property.dto.CreateTransactionDTO;
import br.com.apsoo.property.dto.ReservationDTO;
import br.com.apsoo.property.dto.TransactionDTO;
import br.com.apsoo.property.dto.UserDTO;
import br.com.apsoo.property.entity.property.ApartmentPropertyCreator;
import br.com.apsoo.property.entity.property.HousePropertyCreator;
import br.com.apsoo.property.entity.property.Property;
import br.com.apsoo.property.model.PropertyModel;
import br.com.apsoo.property.model.ReservationModel;
import br.com.apsoo.property.repository.ReservationRepository;
import br.com.apsoo.property.service.PropertyService;
import br.com.apsoo.property.service.ReservationService;
import br.com.apsoo.property.service.TransactionService;
import br.com.apsoo.property.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PropertyService propertyService;
    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    ReservationServiceImpl(ReservationRepository reservationRepository, PropertyService propertyService,
                           UserService userService, TransactionService transactionService) {
        this.reservationRepository = reservationRepository;
        this.propertyService = propertyService;
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @Override
    @Transactional
    public ReservationDTO create(ReservationDTO reservationDTO) {

        PropertyModel propertyModel = propertyService.getByIdModel(reservationDTO.property().id());
        UserDTO userDTO = userService.getById(reservationDTO.userId());
        if (userDTO.id() == null) {
            throw new NotFoundException("User not found");
        }

        List<ReservationModel> reservationModels =
                reservationRepository.findReservationsWithinPeriodAndProperty(reservationDTO.startDate(),
                        reservationDTO.endDate(), reservationDTO.property().id());

        if (!reservationModels.isEmpty()) {
            throw new RuntimeException("Property already reserved for this period");
        }

        ReservationModel reservationModel = new ReservationModel(reservationDTO);
        reservationModel.setProperty(propertyModel);

        Property validProperty;
        switch (propertyModel.getType()){
            case "house":
                validProperty = new HousePropertyCreator().createProperty();
                validProperty.validateReservation(reservationModel);
                break;
            case "apartment":
                validProperty = new ApartmentPropertyCreator().createProperty();
                validProperty.validateReservation(reservationModel);
                break;
            default:
                throw new IllegalArgumentException("Invalid type");
        }

        reservationModel = reservationRepository.save(reservationModel);

        TransactionDTO transactionDTO = transactionService.create(new CreateTransactionDTO( reservationModel.getUserId(), reservationModel.getId(),
                reservationModel.getTotalValue(), "PIX"));

        return new ReservationDTO(reservationModel,transactionDTO);
    }

    @Override
    public ReservationModel findById(Long id) {

        return reservationRepository.findById(id).orElseThrow(NotFoundException::new);

    }

    @Override
    public List<ReservationDTO> findByUserId(Long id) {
        return reservationRepository.findByUserId(id).stream().map(ReservationDTO::new).toList();
    }

}
