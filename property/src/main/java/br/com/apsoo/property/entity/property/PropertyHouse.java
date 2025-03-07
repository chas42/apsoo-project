package br.com.apsoo.property.entity.property;

import br.com.apsoo.property.model.PropertyModel;
import br.com.apsoo.property.model.ReservationModel;

public class PropertyHouse implements Property {

    @Override
    public void validateProperty(PropertyModel propertyModel) {
        if (propertyModel.getMaxGuests() > propertyModel.getNumberRooms() * 3) {
            throw new IllegalArgumentException("Houses can have a maximum of 3 times the number of rooms as guests.");
        }
    }

    @Override
    public void validateReservation(ReservationModel reservationModel) {
        if (reservationModel.getEndDate().isAfter(reservationModel.getStartDate().plusMonths(18))) {
            throw new IllegalArgumentException("Houses can be booked for a maximum of 18 months.");
        }
    }
}
