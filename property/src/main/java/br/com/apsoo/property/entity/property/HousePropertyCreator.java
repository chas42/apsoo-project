package br.com.apsoo.property.entity.property;

public class HousePropertyCreator extends PropertyCreator {

    @Override
    public Property createProperty() {
        return new PropertyHouse();
    }

}
