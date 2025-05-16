package com.thomazcollet.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomazcollet.demo.repositories.TasksRepository;


import com.thomazcollet.demo.models.Tasks;
import com.thomazcollet.demo.models.Users;

import java.util.Optional;

@Service
public class TasksService {

    @Autowired
    private TasksRepository tasksRepository;

    private UserService userService;

    public Tasks findById(Long id) {

        Optional<Tasks> tasks = this.tasksRepository.findById(id);
        return tasks.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrada! Id: " + id + ", tipo: " + Tasks.class.getName()));
    }

    @Transactional
    public Tasks create (Tasks obj){

        Users users = this.userService.findById(obj.getUsers().getId());
        obj.setId(null);
        obj.setUsers(users);
        obj = this.tasksRepository.save(obj);
        return obj;
    }

    @Transactional
    public Tasks update (Tasks obj){
        Tasks newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.tasksRepository.save(newObj);

    }

    public void delete (Long id){
        findById(id);
        try {
            this.tasksRepository.deleteById(id);
            System.out.println("Tarefa deletada com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel deletar pois há entidades relacionadas!");
        }
    }
}
