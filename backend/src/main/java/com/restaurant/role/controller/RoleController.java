package com.restaurant.role.controller;


import com.restaurant.role.domains.ERole;
import com.restaurant.role.entities.RoleEntity;
import com.restaurant.role.exceptions.RoleException;
import com.restaurant.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {
 private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping()
    ResponseEntity<?> saveRole(@Valid @RequestBody ERole role){
        log.info("saving role.");
    roleService.saveRole(new RoleEntity().setName(role));

      return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateRole(@PathVariable Integer id, @Valid @RequestBody RoleEntity role) {
        log.info("update role.");
        if (id == null || id == 0) {
            log.error("Role Id can't be null or zero.");
            throw new RoleException("Role Id can't be null or zero.");
        }
        role.setId(id);
        roleService.saveRole(role);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteRole(@PathVariable Integer id) {
        log.info("delete role.");
        if (id == null || id == 0) {
            log.error("Role Id can't be null or zero.");
            throw new RoleException("Role Id can't be null or zero.");
        }
        if(!roleService.existsRoleById(id)){
            log.error("Role not found by Id: {}", id);
            throw new RoleException("Role not found by Id: "+ id);
        }
        roleService.deleteRoleById(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping()
    ResponseEntity<?> getRoles(){
     List<RoleEntity> roles= roleService.getRoles();

        return ResponseEntity.ok(roles);
    }


}
