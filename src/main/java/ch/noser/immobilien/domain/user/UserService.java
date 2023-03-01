package ch.noser.immobilien.domain.user;



public interface UserService {


    User addUser(User user);


    User findByName(String firstname, String lastname);











}
