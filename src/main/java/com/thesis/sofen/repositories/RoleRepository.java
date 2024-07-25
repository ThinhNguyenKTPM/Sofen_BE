package com.thesis.sofen.repositories;

import com.thesis.sofen.common.ERole;
import com.thesis.sofen.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(ERole role);
}
