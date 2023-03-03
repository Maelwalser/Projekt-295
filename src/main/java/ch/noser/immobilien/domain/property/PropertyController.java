package ch.noser.immobilien.domain.property;


import ch.noser.immobilien.domain.property.dto.PropertyDto;
import ch.noser.immobilien.domain.property.dto.PropertyMapper;
import ch.noser.immobilien.domain.property.dto.PropertySingleDto;
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

    private final PropertyService propertyService;
    private final PropertyMapper propertyMapper;

    @Autowired
    public PropertyController(PropertyService propertyService, PropertyMapper propertyMapper) {
        this.propertyService = propertyService;
        this.propertyMapper = propertyMapper;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<PropertyDto> addProperty(@Valid @RequestBody PropertyDto propertyDTO, @PathVariable("userId") UUID userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyMapper.toDTO(propertyService.addProperty(propertyMapper.fromDTO(propertyDTO), userId)));
    }

    @GetMapping("/canton")
    public ResponseEntity<List<PropertyDto>> findPropertiesByCanton(@RequestParam("canton") String canton) {
        return ResponseEntity.ok(propertyMapper.allToDTO(propertyService.findAllByCanton(canton)));
    }


    @GetMapping("/name")
    public ResponseEntity<PropertyDto> findPropertyByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.findByName(name)));
    }


    @PutMapping("/{propertyId}/user/{userId}")
    public ResponseEntity<PropertyDto> updateProperty(@PathVariable("propertyId") UUID propertyId, @PathVariable("userId") UUID userId, @Valid @RequestBody PropertyDto propertyDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propertyMapper.toDTO(propertyService.updateProperty(propertyId, userId, propertyMapper.fromDTO(propertyDTO))));
    }


    @DeleteMapping("/{propertyId}/user/{userId}")
    public ResponseEntity<Void> deleteProperty(@PathVariable("propertyId") UUID propertyId, @PathVariable("userId") UUID userId) {
        propertyService.deleteProperty(propertyId, userId);
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/{propertyId}/canton/user/{userId}")
    public ResponseEntity<PropertyDto> updatePropertyCanton(@Valid @RequestBody PropertySingleDto.Canton propertyDTO, @PathVariable("propertyId") UUID propertyId, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertyCanton(propertyId, userId, propertyDTO.getCanton())));
    }


    @PatchMapping("/{propertyId}/name/user/{userId}")
    public ResponseEntity<PropertyDto> updatePropertyName(@Valid @RequestBody PropertySingleDto.Name propertyDTO, @PathVariable("propertyId") UUID propertyId, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertyName(propertyId, userId, propertyDTO.getName())));
    }


    @PatchMapping("/{propertyId}/price/user/{userId}")
    public ResponseEntity<PropertyDto> updatePropertyPrice(@Valid @RequestBody PropertySingleDto.Price propertyDTO, @PathVariable("propertyId") UUID propertyId, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertyPrice(propertyId, userId, propertyDTO.getPrice())));
    }

    @PatchMapping("/{propertyId}/size/user/{userId}")
    public ResponseEntity<PropertyDto> updatePropertySize(@Valid @RequestBody PropertySingleDto.Size propertyDTO, @PathVariable("propertyId") UUID propertyId, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertySize(propertyId, userId, propertyDTO.getSize())));
    }


    @PatchMapping("/{propertyId}/url/user/{userId}")
    public ResponseEntity<PropertyDto> updatePropertyUrl(@Valid @RequestBody PropertySingleDto.Url propertyDTO, @PathVariable("propertyId") UUID propertyId, @PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(propertyMapper.toDTO(propertyService.updatePropertyUrl(propertyId, userId, propertyDTO.getUrl())));
    }


}
