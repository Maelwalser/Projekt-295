package ch.noser.immobilien.domain.role;


import ch.noser.immobilien.domain.role.dto.RoleDTO;
import ch.noser.immobilien.domain.role.dto.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;
    private RoleMapper roleMapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO roleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleMapper.toDTO(roleService.addRole(roleMapper.fromDTO(roleDTO))));
    }
}
