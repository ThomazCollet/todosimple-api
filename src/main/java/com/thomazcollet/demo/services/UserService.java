package com.thomazcollet.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thomazcollet.demo.repositories.UsersRepository;
import com.thomazcollet.demo.models.*;

import java.util.Optional;


@Service
public class UserService {

    // Atributos
    @Autowired
    private UsersRepository usersRepository;


    // Métodos

    public Users findById(Long id) {
        Optional<Users> users = this.usersRepository.findById(id);
        return users.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado!" + " Id:" + id + ", Tipo: " + Users.class.getName()));
    }

    @Transactional
    public Users create(Users obj) {
        obj.setId(null);
        obj = this.usersRepository.save(obj);
        return obj;
    }

    @Transactional
    public Users update(Users obj) {
        Users newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return this.usersRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        System.out.println("Usuário deletado com sucesso!");
        try {
            this.usersRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel deletar pois há entidades relacionadas!");
        }
    }
}