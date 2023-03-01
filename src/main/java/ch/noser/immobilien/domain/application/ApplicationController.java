package ch.noser.immobilien.domain.application;


import ch.noser.immobilien.domain.application.dto.ApplicationDTO;
import ch.noser.immobilien.domain.application.dto.ApplicationMapper;
import ch.noser.immobilien.domain.property.Property;
import ch.noser.immobilien.domain.user.dto.UserDTO;
import ch.noser.immobilien.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    ApplicationService applicationService;
    ApplicationMapper applicationMapper;

    @Autowired
    public ApplicationController(ApplicationService applicationService, ApplicationMapper applicationMapper){
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
    }


    @PostMapping({"/applyFor/{propertyId}/byUser/{userId}"})
    public ResponseEntity<ApplicationDTO> createApplication(@PathVariable("propertyId")UUID propertyId, @PathVariable("userId")UUID userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationMapper.toDTO(applicationService.createApplication(userId, propertyId)));
    }


    @PostMapping({"/accept/{applicationId}/byUser/{userId}"})
    public ResponseEntity<ApplicationDTO> acceptApplication(@PathVariable("applicationId") UUID applicationId, @PathVariable("userId") UUID userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationMapper.toDTO(applicationService.acceptApplication(userId, applicationId)));
    }


    @PostMapping({"/deny/{applicationId}/byUser/{userId}"})
    public ResponseEntity<ApplicationDTO> denyApplication(@PathVariable("applicationId") UUID applicationId, @PathVariable("userId") UUID userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationMapper.toDTO(applicationService.denyApplication(userId, applicationId)));
    }
}
