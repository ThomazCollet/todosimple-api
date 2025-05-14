package com.thomazcollet.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thomazcollet.demo.models.Users;

@Repository
public interface UsersRepository extends JpaRepository <Users, Long>{
    
}
