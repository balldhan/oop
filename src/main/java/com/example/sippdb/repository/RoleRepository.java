package com.example.sippdb.repository;

import com.example.sippdb.model.Role;
import com.example.sippdb.model.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
