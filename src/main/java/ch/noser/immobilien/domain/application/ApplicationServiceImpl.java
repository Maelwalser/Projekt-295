package ch.noser.immobilien.domain.application;

import ch.noser.immobilien.domain.property.Property;
import ch.noser.immobilien.domain.property.PropertyService;
import ch.noser.immobilien.domain.user.User;
import ch.noser.immobilien.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApplicationServiceImpl implements ApplicationService {


    private final ApplicationRepository applicationRepository;
    private final UserService userService;
    private final PropertyService propertyService;


    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, UserService userService, PropertyService propertyService) {
        this.applicationRepository = applicationRepository;
        this.userService = userService;
        this.propertyService = propertyService;
    }


    @Override
    public Application acceptApplication(UUID userId, UUID applicationId) {
        User user = userService.findUserById(userId);
        Application currentApplication = getApplicationById(applicationId);
        if (user.getRole().getName().equals("Agent") && currentApplication.getProperty().getUser().equals(user)) {
            for (Application application : applicationRepository.findAllByProperty(currentApplication.getProperty())) {
                application.setStatus(Status.DENIED);
                applicationRepository.save(application);
            }
            currentApplication.setStatus(Status.ACCEPTED);
            applicationRepository.save(currentApplication);
            return currentApplication;
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Application createApplication(UUID userId, UUID propertyId) {
        User user = userService.findUserById(userId);
        Property property = propertyService.findPropertyById(propertyId);
        boolean multipleApplications = false;
        for (Application application : applicationRepository.findAllByProperty(property)) {
            multipleApplications = application.getUser().equals(user);
        }
        if (user.getRole().getName().equals("Client") && !multipleApplications) {
            Application application = new Application();
            application.setUser(user);
            application.setProperty(property);
            application.setStatus(Status.PENDING);
            return applicationRepository.save(application);
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }


    @Override
    public Application denyApplication(UUID userId, UUID applicationId) {
        User user = userService.findUserById(userId);
        Application application = getApplicationById(applicationId);
        if (user.getRole().getName().equals("Agent") && application.getProperty().getUser().equals(user)) {
            application.setStatus(Status.DENIED);
            return applicationRepository.save(application);
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Application getApplicationById(UUID id) {
        Optional<Application> applicationOptional = applicationRepository.findById(id);
        if (applicationOptional.isPresent()) {
            return applicationOptional.get();
        }
        throw new NoSuchElementException("No application with id " + id + " found");
    }


}
