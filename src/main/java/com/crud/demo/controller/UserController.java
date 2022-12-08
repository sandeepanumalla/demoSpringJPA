package com.crud.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.demo.entities.User;
import com.crud.demo.exception.ResourceNotFoundException;
import com.crud.demo.repository.UsersRepository;
import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    //get all users 
    @Autowired
    private UsersRepository usersRepository;

    @GetMapping
    public List<User> getAllUsers(){
        return this.usersRepository.findAll();
    }

    //get users by id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") long userId){
        return this.usersRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User not found with "+ userId)
        );
    }

    //create user
    @PostMapping("/")
    public User createUser(@RequestBody User user){
        return this.usersRepository.save(user);
    }

    //update user by id
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable(value = "id") long userId){
        User existingUser =  this.usersRepository.findById(userId).orElseThrow(() -> 
        new ResourceNotFoundException("User not found with "+ userId));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        return this.usersRepository.save(existingUser);
    }

    // delete user by id 
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteById(@PathVariable("id") long userId){
        User existingUser =  this.usersRepository.findById(userId).orElseThrow(() -> 
        new ResourceNotFoundException("User not found with "+ userId));
         this.usersRepository.delete(existingUser);
         return ResponseEntity.ok().build();
    }
}
