package ch.noser.immobilien.domain.property;


import ch.noser.immobilien.domain.property.dto.PropertyDTO;
import ch.noser.immobilien.domain.property.dto.PropertyMapper;
import ch.noser.immobilien.domain.property.dto.PropertySingleDTO;
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
    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper) {
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
    }

    @PostMapping({"/add/byUser/{id}"})
    public ResponseEntity<PropertyDTO> addProperty(@Valid @RequestBody PropertyDTO propertyDTO, @PathVariable("id") UUID userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyMapper.toDTO(propertyService.addProperty(propertyMapper.fromDTO(propertyDTO), userId)));
    }

    @GetMapping({"/canton"})
    public ResponseEntity<List<PropertyDTO>> findPropertiesByCanton(@RequestParam("canton") String canton) {
        return ResponseEntity.ok(propertyMapper.allToDTO(propertyService.findAllByCanton(canton)));
    }


    @GetMapping({"/name"})
    public ResponseEntity<PropertyDTO> findPropertyByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.findByName(name)));
    }


    @PostMapping({"/update/{id}/byUser/{userId}"})
    public ResponseEntity<PropertyDTO> updateProperty(@PathVariable("id") UUID id, @PathVariable("userId") UUID userId, @Valid @RequestBody PropertyDTO propertyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyMapper.toDTO(propertyService.updateProperty(id, userId, propertyMapper.fromDTO(propertyDTO))));
    }


    @DeleteMapping({"/{id}/byUser/{userId}"})
    public void deleteProperty(@PathVariable("id") UUID id, @PathVariable("userId") UUID userId) {
        propertyService.deleteProperty(id, userId);
    }


    @PatchMapping({"update/{id}/canton/byUser/{userId}"})
    public ResponseEntity<PropertyDTO> updatePropertyCanton(@Valid @RequestBody PropertySingleDTO.Canton propertyDTO, @PathVariable("id") UUID id, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertyCanton(id, userId, propertyDTO.getCanton())));
    }


    @PatchMapping({"update/{id}/name/byUser/{userId}"})
    public ResponseEntity<PropertyDTO> updatePropertyName(@Valid @RequestBody PropertySingleDTO.Name propertyDTO, @PathVariable("id") UUID id, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertyName(id, userId, propertyDTO.getName())));
    }


    @PatchMapping({"update/{id}/price/byUser/{userId}"})
    public ResponseEntity<PropertyDTO> updatePropertyPrice(@Valid @RequestBody PropertySingleDTO.Price propertyDTO, @PathVariable("id") UUID id, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertyPrice(id, userId, propertyDTO.getPrice())));
    }

    @PatchMapping({"update/{id}/size/byUser/{userId}"})
    public ResponseEntity<PropertyDTO> updatePropertySize(@Valid @RequestBody PropertySingleDTO.Size propertyDTO, @PathVariable("id") UUID id, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertySize(id, userId, propertyDTO.getSize())));
    }


    @PatchMapping({"update/{id}/url/byUser/{userId}"})
    public ResponseEntity<PropertyDTO> updatePropertyUrl(@Valid @RequestBody PropertySingleDTO.Url propertyDTO, @PathVariable("id") UUID id, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertyUrl(id, userId, propertyDTO.getUrl())));
    }


}
