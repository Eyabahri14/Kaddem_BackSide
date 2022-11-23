package com.esprit.kaddemback.services;

import com.esprit.kaddemback.entities.Role;
import com.esprit.kaddemback.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl {

    @Autowired
    RoleRepository roleRepository;
    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }


}
