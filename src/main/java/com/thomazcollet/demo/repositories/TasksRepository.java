package com.thomazcollet.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.thomazcollet.demo.models.Tasks;
import java.util.List;


@Repository
public interface TasksRepository extends JpaRepository <Tasks, Long>{
    List<Tasks> findByUsers_Id(Long id);
   // @Query(value = "SELECT t FROM Tasks t WHERE t.users.id = :id")
   // List<Tasks> findByUser_Id(@Param("id") Long id);

}
