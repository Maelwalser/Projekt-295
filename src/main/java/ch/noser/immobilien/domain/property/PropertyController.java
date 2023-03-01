package ch.noser.immobilien.domain.property;


import ch.noser.immobilien.domain.property.dto.PropertyDTO;
import ch.noser.immobilien.domain.property.dto.PropertyMapper;
import ch.noser.immobilien.domain.user.dto.UserDTO;
import ch.noser.immobilien.domain.user.dto.UserMapper;
import jakarta.validation.Valid;
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
    private PropertyMapper propertyMapper;

    @Autowired
    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper){
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
    }

    @PostMapping({"/add/byUser/{id}"})
    public ResponseEntity<PropertyDTO> addProperty(@Valid @RequestBody PropertyDTO propertyDTO, @PathVariable("id")UUID userId){
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyMapper.toDTO(propertyService.addProperty(propertyMapper.fromDTO(propertyDTO), userId)));
    }

    @GetMapping({"/canton"})
    public ResponseEntity<List<PropertyDTO>> findPropertiesByCanton(@RequestParam("canton") String canton){
        return ResponseEntity.ok(propertyMapper.allToDTO(propertyService.findAllByCanton(canton)));
    }


    @GetMapping({"/name"})
    public ResponseEntity<PropertyDTO> findPropertyByName(@RequestParam("name") String name){
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.findByName(name)));
    }


    @PostMapping({"/update/{id}/byUser/{userId}"})
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable("id") UUID id,@PathVariable("userId") UUID userId, @RequestBody PropertyDTO propertyDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyMapper.toDTO(propertyService.updateProperty(id, userId, propertyMapper.fromDTO(propertyDTO))));
    }


    @DeleteMapping({"/{id}/byUser/{userId}"})
    public void deleteProperty(@PathVariable("id") UUID id, @PathVariable("userId") UUID userId){
        propertyService.deleteProperty(id,userId);
    }


}
