package br.com.apsoo.property.repository;

import br.com.apsoo.property.model.ReviewModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewModel, Long> {
}
