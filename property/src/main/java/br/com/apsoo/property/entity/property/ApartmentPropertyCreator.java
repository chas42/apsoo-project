package br.com.apsoo.property.entity.property;

public class ApartmentPropertyCreator extends PropertyCreator{
    @Override
    public Property createProperty() {
        return new PropertyApartment();
    }
}
