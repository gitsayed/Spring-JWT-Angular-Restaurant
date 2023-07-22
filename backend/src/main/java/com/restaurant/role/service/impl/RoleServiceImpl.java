package com.restaurant.role.service.impl;

import com.restaurant.jwts.repository.RoleRepository;
import com.restaurant.role.domains.ERole;
import com.restaurant.role.entities.RoleEntity;
import com.restaurant.role.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean existsRoleById(Integer id) {
        return  this.roleRepository.existsById(id);
    }

    @Override
    public boolean existsRoleByName(ERole name) {
        return this.roleRepository.existsByName(name);
    }

    @Override
    public RoleEntity findRoleById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public void saveRole(RoleEntity roleEntity) {
         roleRepository.save(roleEntity);
    }

    @Override
    public void saveRoles(List<RoleEntity> roleEntities) {
        roleRepository.saveAll(roleEntities);
    }
    @Override
    public void deleteRoleEntity(RoleEntity roleEntity) {
        roleRepository.delete(roleEntity);
    }
    @Override
    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }

    @Override
    public List<RoleEntity> getRoles() {
        return roleRepository.findAll();
    }
}
