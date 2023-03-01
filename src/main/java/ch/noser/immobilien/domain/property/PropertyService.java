package ch.noser.immobilien.domain.property;

import ch.noser.immobilien.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface PropertyService {


    Property addProperty(Property property, User user);
    void deleteProperty(UUID id, User user);

    Property updateProperty(UUID id, User user,Property newProperty);

    List<Property> findAllByCanton(String canton);

    Property findByName(String name);
}
