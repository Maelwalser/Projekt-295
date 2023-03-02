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

    private final PropertyRepository propertyRepository;
    private final UserService userService;

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
    public void deleteProperty(UUID propertyId, UUID userId) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
            if (optionalProperty.isPresent() && optionalProperty.get().getUser().equals(user)) {
                propertyRepository.deleteById(propertyId);
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
    public Property updateProperty(UUID propertyId, UUID userId, Property newProperty) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(propertyId);
            if (property.getUser().equals(user)) {
                newProperty.setId(property.getId());
                newProperty.setUser(property.getUser());
                return propertyRepository.save(newProperty);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property findPropertyById(UUID propertyId) {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);
        if (optionalProperty.isPresent()) {
            return optionalProperty.get();
        }
        throw new NoSuchElementException("No property with id " + propertyId + " found");
    }

    @Override
    public Property updatePropertyCanton(UUID propertyId, UUID userId, String canton) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(propertyId);
            if (property.getUser().equals(user)) {
                property.setCanton(canton);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property updatePropertyName(UUID propertyId, UUID userId, String name) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(propertyId);
            if (property.getUser().equals(user)) {
                property.setName(name);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property updatePropertyPrice(UUID propertyId, UUID userId, int price) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(propertyId);
            if (property.getUser().equals(user)) {
                property.setPrice(price);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property updatePropertySize(UUID propertyId, UUID userId, int size) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(propertyId);
            if (property.getUser().equals(user)) {
                property.setSize(size);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }

    @Override
    public Property updatePropertyUrl(UUID propertyId, UUID userId, String url) {
        User user = userService.findUserById(userId);
        if (user.getRole().getName().equals("Agent")) {
            Property property = findPropertyById(propertyId);
            if (property.getUser().equals(user)) {
                property.setUrl(url);
                return propertyRepository.save(property);
            }
        }
        throw new HttpServerErrorException(HttpStatus.UNAUTHORIZED);
    }


}
