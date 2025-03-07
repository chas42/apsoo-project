package br.com.apsoo.property.service.impl;

import br.com.apsoo.property.dto.CreateReviewDTO;
import br.com.apsoo.property.dto.ReviewDTO;
import br.com.apsoo.property.model.ReservationModel;
import br.com.apsoo.property.model.ReviewModel;
import br.com.apsoo.property.repository.ReviewRepository;
import br.com.apsoo.property.service.ReservationService;
import br.com.apsoo.property.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReservationService reservationService;

    @Autowired
    ReviewServiceImpl(ReviewRepository reviewRepository, ReservationService reservationService) {
        this.reviewRepository = reviewRepository;
        this.reservationService = reservationService;
    }

    @Override
    public ReviewDTO create(CreateReviewDTO reviewDTO) {

        ReservationModel reservationModel = reservationService.findById(reviewDTO.reservationId());

        ReviewModel newReviewModel = new ReviewModel(reviewDTO);
        newReviewModel.setReservation(reservationModel);

        newReviewModel = reviewRepository.save(newReviewModel);

        return new ReviewDTO(newReviewModel);
    }

}
