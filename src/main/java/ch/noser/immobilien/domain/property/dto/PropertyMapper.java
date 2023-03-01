package ch.noser.immobilien.domain.property.dto;


import ch.noser.immobilien.domain.property.Property;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyDTO toDTO(Property property);
    Property fromDTO(PropertyDTO propertyDTO);
    List<PropertyDTO> allToDTO(List<Property> properties);

}
