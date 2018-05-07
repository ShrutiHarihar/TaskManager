/*
 * @author Shruti Harihar
 */
package com.walmart.tasklist.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.walmart.tasklist.models.Task;


/**
 * The Interface TaskRepository.
 */
@Repository
public interface TaskRepository extends MongoRepository<Task, String> {
	
	/**
	 * Find by assigned to.
	 *
	 * @param assignedTo the assigned to
	 * @return the list
	 */
	List<Task> findByAssignedTo(String assignedTo);

	/**
	 * Find by frequency.
	 *
	 * @param frequency the frequency
	 * @return the list
	 */
	List<Task> findByFrequency(int frequency);
}