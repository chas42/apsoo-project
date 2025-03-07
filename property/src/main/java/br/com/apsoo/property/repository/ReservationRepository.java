package br.com.apsoo.property.repository;

import br.com.apsoo.property.model.ReservationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<ReservationModel, Long> {

    @Query("SELECT r FROM ReservationModel r WHERE r.startDate <= :endDate AND r.endDate >= :startDate AND" +
            " r.property.id = :propertyId")
    List<ReservationModel> findReservationsWithinPeriodAndProperty(@Param("startDate") LocalDateTime startDate,
                                                                   @Param("endDate") LocalDateTime endDate,
                                                                   @Param("propertyId") Long propertyId);


    List<ReservationModel> findByUserId(Long id);

}
