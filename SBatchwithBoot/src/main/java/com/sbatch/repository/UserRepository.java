package com.sbatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbatch.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	
	
	
	
}
