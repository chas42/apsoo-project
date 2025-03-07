package br.com.apsoo.property.entity.property;

import br.com.apsoo.property.model.PropertyModel;
import br.com.apsoo.property.model.ReservationModel;

public class PropertyApartment implements Property {

    @Override
    public void validateProperty(PropertyModel propertyModel) {

        if (propertyModel.getMaxGuests() > propertyModel.getNumberRooms() * 2) {
            throw new IllegalArgumentException("Apartments can have a maximum of 2 times the number of rooms as " +
                    "guests.");
        }

    }

    @Override
    public void validateReservation(ReservationModel reservationModel) {
        if (reservationModel.getEndDate().isAfter(reservationModel.getStartDate().plusMonths(12))) {
            throw new IllegalArgumentException("Apartments can be booked for a maximum of 18 months.");
        }
    }
}
