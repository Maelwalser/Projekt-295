package ch.noser.immobilien.domain.property.dto;


import ch.noser.immobilien.domain.property.Property;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyDto toDTO(Property property);

    Property fromDTO(PropertyDto propertyDTO);

    List<PropertyDto> allToDTO(List<Property> properties);


}
