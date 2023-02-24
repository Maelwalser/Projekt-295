package ch.noser.immobilien.domain.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{


    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }


    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByName(String name) {
        Optional<Role> optionalRole = roleRepository.findAllByName(name);
        if(optionalRole.isPresent()){
            return optionalRole.get();
        }
        throw new NoSuchElementException("No role found with name " + name);
    }
}
