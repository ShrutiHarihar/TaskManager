/**
 * @author Shruti Harihar
 */
package com.walmart.tasklist;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.walmart.tasklist.models.Task;
import com.walmart.tasklist.models.User;
import com.walmart.tasklist.repositories.TaskRepository;
import com.walmart.tasklist.repositories.UserRepository;


/**
 * The Class TaskSchedules.
 */
@Component
public class TaskSchedules {

	/** The task repository. */
	@Autowired
	TaskRepository taskRepository;

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/**
	 * Assign task every hour.
	 */
	@Scheduled(cron = "0 0 * ? * *")
	public void assignTaskEveryHour() {

		assignTask(1);

	}

	/**
	 * Assign task every 3 hours.
	 */
	@Scheduled(cron = "0 0 */3 ? * *")
	public void assignTaskEvery3Hours() {

		assignTask(3);

	}

	/**
	 * Assign task every 6 hours.
	 */
	@Scheduled(cron = "0 0 */6 ? * *")
	public void assignTaskEvery6Hours() {
		assignTask(6);

	}

	/**
	 * Assign task every 12 hours.
	 */
	@Scheduled(cron = "0 0 */12 ? * *")
	public void assignTaskEvery12Hours() {
		assignTask(12);
	}

	/**
	 * Check rank.
	 */
	@Scheduled(cron = "0 0 * ? * *")
	public void checkRank() {
		List<Task> tasks = taskRepository.findAll();
		for (int i = 0; i < tasks.size(); i++) {

			if (tasks.get(i).getStatus() != 2) {
				Task task = tasks.get(i);
				long duration = Duration.between(task.getCreatedAt(), LocalDateTime.now()).toHours();
				if (duration > task.getFrequency()) {
					task.setRank(2);
					taskRepository.save(task);
				} else if (duration > 3) {
					if (task.getRank() < 2) {
						task.setRank(task.getRank() + 1);
						taskRepository.save(task);
					}

				}
			}
		}
	}

	/**
	 * Assign task.
	 *
	 * @param frequency the frequency
	 */
	private void assignTask(int frequency) {
		List<Task> tasks = taskRepository.findByFrequency(frequency);
		List<User> users = userRepository.findByRole("Employee");

		int tasks_count = tasks.size();
		int users_count = users.size();

		Random random = new Random();
		Set<Integer> generated = new LinkedHashSet<Integer>();
		while (generated.size() < tasks_count) {
			Integer next = random.nextInt(users_count) + 1;
			generated.add(next);
		}

		int count = 1;
		for (Integer i : generated) {
			Task task = new Task(tasks.get(count - 1));
			task.setAssignedTo(users.get(i - 1).getUsername());
			taskRepository.save(task);
			count++;
		}
	}

}
