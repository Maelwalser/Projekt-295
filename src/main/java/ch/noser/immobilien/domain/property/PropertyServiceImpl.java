package ch.noser.immobilien.domain.property;

import ch.noser.immobilien.domain.user.User;
import ch.noser.immobilien.domain.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyServiceImpl implements PropertyService{

    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository){
        this.propertyRepository = propertyRepository;
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
    public void deleteProperty(UUID id, User user) {
        if(user.getRole().equals("Agent") && user.getProperties().contains(propertyRepository.findById(id))){
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if(optionalProperty.isPresent()){
            propertyRepository.deleteById(id);
        }
        throw new NoSuchElementException("No property with id " + id + " found!");
    }
}

    @Override
    public Property addProperty(Property property, User user){
            if (user.getRole().equals("Agent")){
                property.setUser(user);
                return propertyRepository.save(property);
            }
            else {
                return null;
            }
        }


    @Override
    public Property updateProperty(UUID id, User user, Property newProperty) {
        if (user.getRole().equals("Agent") && user.getProperties().contains(propertyRepository.findById(id))){
            Optional<Property> optionalProperty = propertyRepository.findById(id);
            if(optionalProperty.isPresent()){
                newProperty.setId(optionalProperty.get().getId());
                return propertyRepository.save(newProperty);
            }
            throw new NoSuchElementException("No property with id " + id + " found!");
        }
        else {
            return null;
        }
    }





    }
