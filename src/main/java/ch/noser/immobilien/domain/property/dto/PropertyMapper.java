package ch.noser.immobilien.domain.property.dto;


import org.mapstruct.Mapper;
import org.springframework.data.mapping.model.Property;

@Mapper(componentModel = "spring")
public interface PropertyMapper {
    PropertyDTO toDTO(Property property);


}
