package ch.noser.immobilien.domain.application.dto;


import ch.noser.immobilien.domain.application.Application;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {
    ApplicationDto toDTO(Application application);

    Application fromDTO(ApplicationDto applicationDTO);


}
