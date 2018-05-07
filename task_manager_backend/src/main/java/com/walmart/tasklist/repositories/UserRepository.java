/*
 * @author Shruti Harihar
 */
package com.walmart.tasklist.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.walmart.tasklist.models.User;


/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

	/**
	 * Find by username.
	 *
	 * @param username the username
	 * @return the user
	 */
	User findByUsername(String username);

	/**
	 * Find by role.
	 *
	 * @param role the role
	 * @return the list
	 */
	List<User> findByRole(String role);
}
