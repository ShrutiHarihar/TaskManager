/*
 * @author Shruti Harihar
 */
package com.walmart.tasklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The Class TaskListApplication.
 */
@SpringBootApplication
@EnableScheduling
public class TaskListApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(TaskListApplication.class, args);
	}
}
