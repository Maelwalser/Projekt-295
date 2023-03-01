package ch.noser.immobilien.domain.property;


import ch.noser.immobilien.domain.user.dto.UserDTO;
import ch.noser.immobilien.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/properties")
public class PropertyController {


    private PropertyService propertyService;
    private UserMapper userMapper;

    @Autowired
    public PropertyController(PropertyService propertyService, UserMapper userMapper){
        this.propertyService = propertyService;
        this.userMapper = userMapper;
    }

    @PostMapping({"/", ""})
    public ResponseEntity<Property> addProperty(@RequestBody Property property, @RequestBody UserDTO user){
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.addProperty(property, userMapper.fromDTO(user)));
    }

    @GetMapping({"/canton"})
    public ResponseEntity<List<Property>> findPropertiesByCanton(@RequestParam("canton") String canton){
        return ResponseEntity.ok(propertyService.findAllByCanton(canton));
    }


    @GetMapping({"/name"})
    public ResponseEntity<Property> findPropertyByName(@RequestParam("name") String name){
        return ResponseEntity.ok(propertyService.findByName(name));
    }


    @PostMapping({"/update/{id}"})
    public ResponseEntity<Property> updateProperty(@PathVariable("id") UUID id, UserDTO userDTO, Property property){
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyService.updateProperty(id, userMapper.fromDTO(userDTO), property));
    }


    @DeleteMapping({"/{id}"})
    public void deleteProperty(@PathVariable("id") UUID id, UserDTO userDTO){
        propertyService.deleteProperty(id, userMapper.fromDTO(userDTO));
    }


}
