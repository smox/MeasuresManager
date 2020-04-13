package ui.converter;

import javafx.util.StringConverter;
import persistence.model.ContainerType;

public class ContainerTypeConverter extends StringConverter<ContainerType> {

    @Override
    public String toString(ContainerType containerType) {
        return containerType.getName();
    }

    @Override
    public ContainerType fromString(String name) {

        ContainerType containerType = new ContainerType();
        containerType.setName(name);

        return containerType;
    }
}
