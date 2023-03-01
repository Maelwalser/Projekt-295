package ch.noser.immobilien.domain.user;


import java.util.UUID;

public interface UserService {


    User addUser(User user);


    User findByName(String firstname, String lastname);

    User findUserById(UUID id);











}
