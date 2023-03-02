package ch.noser.immobilien.domain.property;

import ch.noser.immobilien.domain.user.User;
import ch.noser.immobilien.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PropertyServiceImpl implements PropertyService {

    private PropertyRepository propertyRepository;
    private UserService userService;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, UserService userService) {
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
        if (user.getRole().getName().equals("Agent")) {
            Optional<Property> optionalProperty = propertyRepository.findById(id);
            if (optionalProperty.isPresent() && optionalProperty.get().getUser().equals(user)) {
                propertyRepository.deleteById(id);

            }
        }
    }

    @Override
    public Property addProperty(Property property, UUID userId) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            property.setUser(user);
            return propertyRepository.save(property);
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }


    @Override
    public Property updateProperty(UUID id, UUID userId, Property newProperty) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(id);
            if (property.getUser().equals(user)) {
                newProperty.setId(property.getId());
                newProperty.setUser(property.getUser());
                return propertyRepository.save(newProperty);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property findPropertyById(UUID id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            return optionalProperty.get();
        }
        throw new NoSuchElementException("No property with id " + id + " found");
    }

    @Override
    public Property updatePropertyCanton(UUID id, UUID userId, String canton) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(id);
            if (property.getUser().equals(user)) {
                property.setCanton(canton);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property updatePropertyName(UUID id, UUID userId, String name) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(id);
            if (property.getUser().equals(user)) {
                property.setName(name);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property updatePropertyPrice(UUID id, UUID userId, int price) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(id);
            if (property.getUser().equals(user)) {
                property.setPrice(price);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property updatePropertySize(UUID id, UUID userId, int size) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(id);
            if (property.getUser().equals(user)) {
                property.setSize(size);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property updatePropertyUrl(UUID id, UUID userId, String url) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(id);
            if (property.getUser().equals(user)) {
                property.setUrl(url);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }


}
