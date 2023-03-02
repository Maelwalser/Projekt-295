package ch.noser.immobilien.domain.application;


import ch.noser.immobilien.domain.application.dto.ApplicationDto;
import ch.noser.immobilien.domain.application.dto.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    ApplicationService applicationService;
    ApplicationMapper applicationMapper;

    @Autowired
    public ApplicationController(ApplicationService applicationService, ApplicationMapper applicationMapper) {
        this.applicationService = applicationService;
        this.applicationMapper = applicationMapper;
    }


    @PostMapping("/apply/{propertyId}/user/{userId}")
    public ResponseEntity<ApplicationDto> createApplication(@PathVariable("propertyId") UUID propertyId, @PathVariable("userId") UUID userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationMapper.toDTO(applicationService.createApplication(userId, propertyId)));
    }


    @PatchMapping("/accept/{applicationId}/user/{userId}")
    public ResponseEntity<ApplicationDto> acceptApplication(@PathVariable("applicationId") UUID applicationId, @PathVariable("userId") UUID userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationMapper.toDTO(applicationService.acceptApplication(userId, applicationId)));
    }


    @PatchMapping("/deny/{applicationId}/user/{userId}")
    public ResponseEntity<ApplicationDto> denyApplication(@PathVariable("applicationId") UUID applicationId, @PathVariable("userId") UUID userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(applicationMapper.toDTO(applicationService.denyApplication(userId, applicationId)));
    }
}
