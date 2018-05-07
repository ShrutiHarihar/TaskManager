/*
 * @author Shruti Harihar
 */
package com.walmart.tasklist.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.tasklist.models.Task;
import com.walmart.tasklist.repositories.TaskRepository;

/**
 * The Class TaskListController.
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskListController {

	/** The task repository. */
	@Autowired
	TaskRepository taskRepository;

	/**
	 * Gets the all tasks.
	 *
	 * @return all tasks
	 */
	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	/**
	 * Creates the task.
	 *
	 * @param task
	 *            the task
	 * @return the task
	 */
	@PostMapping("/create")
	public Task createTask(@Valid @RequestBody Task task) {

		return taskRepository.save(task);
	}

	/**
	 * Gets the task by id.
	 *
	 * @param id
	 *            the id
	 * @return the task by id
	 */
	@GetMapping(value = "/tasks/{id}")
	public ResponseEntity<Task> getTaskById(@PathVariable("id") String id) {
		return taskRepository.findById(id).map(task -> ResponseEntity.ok().body(task))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Update task.
	 *
	 * @param id
	 *            the id
	 * @param task
	 *            the task
	 * @return the response entity
	 */
	@PutMapping(value = "/tasks/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable("id") String id, @Valid @RequestBody Task task) {
		return taskRepository.findById(id).map(taskData -> {
			taskData.setTitle(task.getTitle());
			taskData.setNotes(task.getNotes());
			taskData.setAssignedTo(task.getAssignedTo());
			taskData.setCreatedBy(task.getCreatedBy());
			taskData.setFeedback(task.getFeedback());
			taskData.setFrequency(task.getFrequency());
			taskData.setStatus(task.getStatus());
			Task updatedTask = taskRepository.save(taskData);
			return ResponseEntity.ok().body(updatedTask);
		}).orElse(ResponseEntity.notFound().build());
	}

	/**
	 * View tasks.
	 *
	 * @param userid
	 *            the userid
	 * @return the list
	 */
	@GetMapping(value = "/view/{userid}")
	public List<Task> viewTasks(@PathVariable("userid") String userid) {
		return taskRepository.findByAssignedTo(userid);
	}

	/**
	 * Delete task.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@DeleteMapping(value = "/tasks/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
		return taskRepository.findById(id).map(task -> {
			taskRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}
}