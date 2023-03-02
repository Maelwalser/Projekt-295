package ch.noser.immobilien.domain.application;

import java.util.UUID;

public interface ApplicationService {

    Application acceptApplication(UUID userId, UUID applicationId);


    Application createApplication(UUID userId, UUID propertyId);

    Application denyApplication(UUID userId, UUID applicationId);


    Application getApplicationById(UUID id);


}
