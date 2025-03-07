package br.com.apsoo.property.entity.property;

import br.com.apsoo.property.model.PropertyModel;
import br.com.apsoo.property.model.ReservationModel;

public interface Property {

    void validateProperty(PropertyModel propertyModel);
    void validateReservation(ReservationModel reservationModel);

}
