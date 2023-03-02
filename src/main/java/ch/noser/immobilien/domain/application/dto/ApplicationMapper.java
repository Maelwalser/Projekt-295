package ch.noser.immobilien.domain.application.dto;


import ch.noser.immobilien.domain.application.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationDTO toDTO(Application application);

    Application fromDTO(ApplicationDTO applicationDTO);


}
