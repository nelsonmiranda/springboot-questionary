package com.miranda.springboot.springbootquestionary.repository;

import org.springframework.data.repository.CrudRepository;

import com.miranda.springboot.springbootquestionary.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
