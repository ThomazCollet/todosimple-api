package com.thomazcollet.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thomazcollet.demo.models.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{
    
}
