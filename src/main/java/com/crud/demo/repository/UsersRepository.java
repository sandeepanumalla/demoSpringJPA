package com.crud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.demo.entities.User;


@Repository
public interface UsersRepository extends JpaRepository<User, Long>{

    
}
