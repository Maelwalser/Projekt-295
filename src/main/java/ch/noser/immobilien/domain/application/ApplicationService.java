package ch.noser.immobilien.domain.application;

import ch.noser.immobilien.domain.property.Property;
import ch.noser.immobilien.domain.user.User;
import org.springframework.context.ApplicationListener;

import java.util.UUID;

public interface ApplicationService {

    Application acceptApplication(UUID userId, UUID applicationId);


    Application createApplication(UUID userId, UUID propertyId);

    Application denyApplication(UUID userId, UUID applicationId);


    Application getApplicationById(UUID id);
}
