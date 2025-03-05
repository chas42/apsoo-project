package br.com.apsoo.property.repository;

import br.com.apsoo.property.model.PropertyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends JpaRepository<PropertyModel, Long> {

    List<PropertyModel> findByUserId(long userId);

}
