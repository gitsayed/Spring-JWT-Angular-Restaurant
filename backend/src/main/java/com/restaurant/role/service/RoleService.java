package com.restaurant.role.service;

import com.restaurant.role.domains.ERole;
import com.restaurant.role.entities.RoleEntity;

import java.util.List;

public interface RoleService {

    boolean existsRoleById(Integer id);
    boolean existsRoleByName(ERole name);
    RoleEntity findRoleById(Integer id);
    void saveRole(RoleEntity roleEntity);
    void saveRoles(List<RoleEntity> roleEntities);
    void deleteRoleById(Integer id);
    void deleteRoleEntity(RoleEntity roleEntity);
    List<RoleEntity> getRoles();

}
