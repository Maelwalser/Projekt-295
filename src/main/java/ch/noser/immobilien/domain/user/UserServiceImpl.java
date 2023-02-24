package ch.noser.immobilien.domain.user;


import ch.noser.immobilien.domain.role.RoleService;
import ch.noser.immobilien.domain.role.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;
    private RoleService roleService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService){
        this.userRepository = userRepository;
        this.roleService = roleService;
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
