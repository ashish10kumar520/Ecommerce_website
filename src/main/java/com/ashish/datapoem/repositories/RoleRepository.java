package com.ashish.datapoem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashish.datapoem.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
