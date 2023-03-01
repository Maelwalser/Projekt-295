package ch.noser.immobilien.domain.application;

import ch.noser.immobilien.domain.property.Property;
import ch.noser.immobilien.domain.user.User;
import org.springframework.context.ApplicationListener;

public interface ApplicationService {

    Application acceptApplication(User user, Application application);


    Application createApplication(User user, Application application, Property property);

    Application denyApplication(User user, Application application);

}
