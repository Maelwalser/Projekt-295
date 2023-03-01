package ch.noser.immobilien.domain.user;

import ch.noser.immobilien.domain.property.PropertyService;
import ch.noser.immobilien.domain.role.RoleService;
import ch.noser.immobilien.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;
    private RoleService roleService;
    private PropertyService propertyService;

    private UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PropertyService propertyService, UserMapper userMapper){
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.propertyService = propertyService;
        this.userMapper = userMapper;
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

    @Override
    public User findByName(String firstname, String lastname) {
        return userRepository.findByFirstnameAndLastnameLike(firstname,lastname);
    }


}
