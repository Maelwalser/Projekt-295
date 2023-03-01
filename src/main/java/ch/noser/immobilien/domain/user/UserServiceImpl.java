package ch.noser.immobilien.domain.user;

import ch.noser.immobilien.domain.property.PropertyService;
import ch.noser.immobilien.domain.role.RoleService;
import ch.noser.immobilien.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements  UserService{

    private UserRepository userRepository;
    private RoleService roleService;

    private UserMapper userMapper;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, UserMapper userMapper){
        this.userRepository = userRepository;
        this.roleService = roleService;
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

    @Override
    public User findUserById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        throw new NoSuchElementException("No user with id "+ id+ " found");
    }


}
