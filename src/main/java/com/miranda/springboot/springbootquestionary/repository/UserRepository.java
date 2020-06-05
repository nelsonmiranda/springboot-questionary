package com.miranda.springboot.springbootquestionary.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.miranda.springboot.springbootquestionary.entity.User;

@RepositoryRestResource(path = "users",collectionResourceRel = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	
	List<User> findByRole(@Param("role") String role);

}
