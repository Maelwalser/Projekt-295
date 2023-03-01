package ch.noser.immobilien.domain.property;

import ch.noser.immobilien.domain.user.User;

import java.util.List;
import java.util.UUID;

public interface PropertyService {


    Property addProperty(Property property, UUID userId);
    void deleteProperty(UUID id, UUID userId);

    Property updateProperty(UUID id, UUID userId, Property newProperty);

    List<Property> findAllByCanton(String canton);

    Property findByName(String name);

    Property findPropertyById(UUID id);
}
