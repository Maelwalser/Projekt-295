package ch.noser.immobilien.domain.user;

import ch.noser.immobilien.domain.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;



    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    @Override
    public User addUser(User user) {
        if (user.getEmail().matches("(.)*@noseryoung.com")) {
            user.setRole(roleService.findRoleByName("Agent"));
        } else {
            user.setRole(roleService.findRoleByName("Client"));
        }
        return userRepository.save(user);
    }

    @Override
    public User findByName(String firstname, String lastname) {
        return userRepository.findByFirstnameAndLastnameLike(firstname, lastname);
    }

    @Override
    public User findUserById(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new NoSuchElementException("No user with id " + id + " found");
    }



}
