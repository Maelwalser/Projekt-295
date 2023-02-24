package ch.noser.immobilien.domain.role;

public interface RoleService {

    Role addRole(Role role);
    Role findRoleByName(String name);

}
