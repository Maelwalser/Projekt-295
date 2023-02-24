package ch.noser.immobilien.domain.property;

import ch.noser.immobilien.domain.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Property addProperty(Property property) {
        return propertyRepository.save(property);
    }

    @Override
    public void deletePropertyById(UUID id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if(optionalProperty.isPresent()){
            propertyRepository.deleteById(id);
        }
        throw new NoSuchElementException("No property with id " + id + " found!");
    }

    @Override
    public Property updatePropertyById(UUID id, Property property) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if(optionalProperty.isPresent()){
            property.setId(optionalProperty.get().getId());
            return propertyRepository.save(property);
        }
        throw new NoSuchElementException("No property with id " + id + " found!");
    }
}
