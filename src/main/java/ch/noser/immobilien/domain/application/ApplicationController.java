package ch.noser.immobilien.domain.application;


import ch.noser.immobilien.domain.property.Property;
import ch.noser.immobilien.domain.user.dto.UserDTO;
import ch.noser.immobilien.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    ApplicationService applicationService;
    UserMapper userMapper;

    @Autowired
    public ApplicationController(ApplicationService applicationService, UserMapper userMapper){
        this.applicationService = applicationService;
        this.userMapper = userMapper;
    }


    @PostMapping({"", "/"})
    public ResponseEntity<Application> createApplication(@RequestBody UserDTO userDTO,@RequestBody Application application,@RequestBody Property property){
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.createApplication(userMapper.fromDTO(userDTO), application, property));
    }


    @PostMapping({"/accept"})
    public ResponseEntity<Application> acceptApplication(@RequestBody UserDTO userDTO, @RequestBody Application application){
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.acceptApplication(userMapper.fromDTO(userDTO), application));
    }


    @PostMapping({"/deny"})
    public ResponseEntity<Application> denyApplication(@RequestBody UserDTO userDTO, @RequestBody Application application){
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationService.acceptApplication(userMapper.fromDTO(userDTO), application));
    }
}
