package br.com.apsoo.property.service;

import br.com.apsoo.property.dto.CreateReviewDTO;
import br.com.apsoo.property.dto.ReviewDTO;

public interface ReviewService {

    ReviewDTO create(CreateReviewDTO reviewDTO);

}
