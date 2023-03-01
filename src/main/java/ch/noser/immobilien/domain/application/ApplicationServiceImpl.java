package ch.noser.immobilien.domain.application;

import ch.noser.immobilien.domain.property.Property;
import ch.noser.immobilien.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService{


    private ApplicationRepository applicationRepository;


    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
    }


    @Override
    public Application acceptApplication(User user, Application application) {
        //user.getProperties().contains(application.getProperty())
        if(user.getRole().equals("Agent") ){
            for (Application appli : applicationRepository.findAllByProperty(application.getProperty())){
                appli.setStatus("denied");
            }
            application.setStatus("accepted");
            return application;
        }
        return null;
    }

    @Override
    public Application createApplication(User user, Application application, Property property) {
        if (user.getRole().equals("Client") && !applicationRepository.findAllByProperty(property).contains(user) ) {
            application.setUser(user);
            application.setProperty(property);
            return applicationRepository.save(application);
        }
        return null;
    }

    @Override
    public Application denyApplication(User user, Application application) {
        //user.getProperties().contains(application.getProperty())
        if(user.getRole().equals("Agent") ){
            application.setStatus("denied");
            return application;
        }
        return null;
    }


}
