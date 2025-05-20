package com.thomazcollet.demo.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;


import com.thomazcollet.demo.services.UserService;
import com.thomazcollet.demo.models.Users;
import com.thomazcollet.demo.models.Users.CreateUser;
import com.thomazcollet.demo.models.Users.UpdateUser;

@RestController
@RequestMapping("/users")
@Validated
public class UsersController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Users> findById(@PathVariable Long id){
        Users obj =this.userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    @Validated(CreateUser.class)
    public ResponseEntity <Void> create (@Valid @RequestBody Users obj){
        this.userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateUser.class)
    public ResponseEntity <Void> update (@Valid @RequestBody Users obj, @PathVariable Long id){
        obj.setId(id);
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
