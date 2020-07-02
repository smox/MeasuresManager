package ui.converter;

import javafx.util.StringConverter;
import persistence.model.Location;

public class LocationConverter extends StringConverter<Location> {


    @Override
    public String toString(Location location) {
        if(location == null)
            return "";
        return location.getName();
    }

    @Override
    public Location fromString(String name) {

        Location location = new Location();
        location.setName(name);

        return location;
    }
}
