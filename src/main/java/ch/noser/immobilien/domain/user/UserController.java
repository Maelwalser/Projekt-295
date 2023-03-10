package ch.noser.immobilien.domain.user;

import ch.noser.immobilien.domain.user.dto.UserDto;
import ch.noser.immobilien.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;


    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toDTO(userService.addUser(userMapper.fromDTO(userDTO))));
    }

    @GetMapping("/name")
    public ResponseEntity<UserDto> findUserByName(@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname) {
        return ResponseEntity.ok(userMapper.toDTO(userService.findByName(firstname, lastname)));
    }


}
