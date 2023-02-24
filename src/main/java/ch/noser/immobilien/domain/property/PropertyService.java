package ch.noser.immobilien.domain.property;

import java.util.UUID;

public interface PropertyService {


    Property addProperty(Property property);
    void deletePropertyById(UUID id);

    Property updatePropertyById(UUID id, Property property);
}
