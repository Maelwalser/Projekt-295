package ch.noser.immobilien.domain.property;

import ch.noser.immobilien.domain.user.User;
import ch.noser.immobilien.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyServiceImpl implements PropertyService{

    private PropertyRepository propertyRepository;
    private UserService userService;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, UserService userService){
        this.propertyRepository = propertyRepository;
        this.userService = userService;
    }



    @Override
    public List<Property> findAllByCanton(String canton) {
        return propertyRepository.findAllByCanton(canton);
    }

    @Override
    public Property findByName(String name) {
        return propertyRepository.findByNameLike(name);
    }


    @Override
    public void deleteProperty(UUID id, UUID userId) {
        User user = userService.findUserById(userId);
        if(user.getRole().getName().equals("Agent")){
            Optional<Property> optionalProperty = propertyRepository.findById(id);
            if(optionalProperty.isPresent() && optionalProperty.get().getUser().equals(user)){
                propertyRepository.deleteById(id);
        }
            else {
        throw new NoSuchElementException("No property with id " + id + " found!");
            }
    }
}

    @Override
    public Property addProperty(Property property, UUID userId){
            User user = userService.findUserById(userId);
            if (user.getRole().getName().equals("Agent")){
                property.setUser(user);
                return propertyRepository.save(property);
            }
            else {
                return null;
            }
        }


    @Override
    public Property updateProperty(UUID id, UUID userId, Property newProperty) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent") ){
            Optional<Property> optionalProperty = propertyRepository.findById(id);
            if(optionalProperty.isPresent() && optionalProperty.get().getUser().equals(user)){
                newProperty.setId(optionalProperty.get().getId());
                newProperty.setUser(optionalProperty.get().getUser());
                return propertyRepository.save(newProperty);
            }
            throw new NoSuchElementException("No property with id " + id + " found!");
        }
        else {
            return null;
        }
    }

    public Property findPropertyById(UUID id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if(optionalProperty.isPresent()){
            return optionalProperty.get();
        }
        throw new NoSuchElementException("No property with id "+ id+ " found");
    }

    }
