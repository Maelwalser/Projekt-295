package ch.noser.immobilien.domain.user;


import ch.noser.immobilien.domain.role.Role;
import ch.noser.immobilien.domain.role.RoleRepository;
import ch.noser.immobilien.domain.role.RoleService;
import ch.noser.immobilien.domain.role.RoleServiceImpl;
import ch.noser.immobilien.domain.role.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;
    private RoleService roleService;
    private RoleRepository roleRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }


    @Override
    public User addUser(User user) {
        if(user.getEmail().matches("(.)*@noseryoung.ch")){
            user.setRole(roleService.findRoleByName("Agent"));
        }
        else {
            user.setRole(roleService.findRoleByName("Client"));
        }
        return userRepository.save(user);
    }
}
