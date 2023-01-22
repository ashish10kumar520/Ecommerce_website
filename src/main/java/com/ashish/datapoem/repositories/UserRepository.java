package com.ashish.datapoem.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashish.datapoem.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findUserByEmail(String email);
	Optional<User> findByEmail(String email);
	@Query(value = "select wallet from users where id=?1",nativeQuery = true)
	Integer findwalletById(Integer id);

}
