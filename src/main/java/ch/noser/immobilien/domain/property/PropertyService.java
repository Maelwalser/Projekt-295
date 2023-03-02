package ch.noser.immobilien.domain.property;

import java.util.List;
import java.util.UUID;

public interface PropertyService {


    Property addProperty(Property property, UUID userId);

    void deleteProperty(UUID id, UUID userId);

    Property updateProperty(UUID id, UUID userId, Property newProperty);

    List<Property> findAllByCanton(String canton);

    Property findByName(String name);

    Property findPropertyById(UUID id);

    Property updatePropertyCanton(UUID id, UUID userId, String canton);

    Property updatePropertyName(UUID id, UUID userId, String name);

    Property updatePropertyPrice(UUID id, UUID userId, int price);

    Property updatePropertySize(UUID id, UUID userId, int size);

    Property updatePropertyUrl(UUID id, UUID userId, String url);


}
