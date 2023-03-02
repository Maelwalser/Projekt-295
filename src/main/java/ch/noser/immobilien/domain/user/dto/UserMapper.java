package ch.noser.immobilien.domain.user.dto;

import ch.noser.immobilien.domain.user.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    User fromDTO(UserDTO dto);


}
