package ch.noser.immobilien.domain.role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @PostMapping({"", "/"})
    public Role addRole(@RequestBody Role role) {
        return roleService.addRole(role);
    }
}