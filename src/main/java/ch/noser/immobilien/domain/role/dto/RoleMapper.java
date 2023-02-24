package ch.noser.immobilien.domain.role.dto;


import ch.noser.immobilien.domain.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role fromDTO(RoleDTO roleDTO);
    RoleDTO toDTO(Role role);
}
