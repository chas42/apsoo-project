package br.com.apsoo.property.controller;

import br.com.apsoo.property.dto.CreateReviewDTO;
import br.com.apsoo.property.dto.PropertyDTO;
import br.com.apsoo.property.dto.ReviewDTO;
import br.com.apsoo.property.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createProperty(@RequestBody CreateReviewDTO createReviewDTO) {
        return ResponseEntity.ok(reviewService.create(createReviewDTO));
    }

}
