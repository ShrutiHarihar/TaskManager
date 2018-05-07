/*
 * @author Shruti Harihar
 */
package com.walmart.tasklist.models;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class Task.
 */
@Document(collection = "todos")
@JsonIgnoreProperties(value = { "createdAt" }, allowGetters = true)
public class Task {

	/** The id. */
	@Id
	private String id;

	/** The title. */
	@NotBlank
	@Indexed(unique = true)
	private String title;

	/** The created at. */
	private LocalDateTime createdAt = LocalDateTime.now();

	/** The created by. */
	private String createdBy;

	/** The assigned to. */
	private String assignedTo;

	/** The notes. */
	private String[] notes = new String[0];

	/** The feedback. */
	private String[] feedback = new String[0];

	/** The status. */
	private int status;

	/** The frequency. */
	private int frequency;

	/** The rank. */
	private int rank;

	/**
	 * Instantiates a new task.
	 */
	public Task() {
		super();
	}

	public Task(Task task) {
		super();
		this.title = "Recurring Assignment" + task.title + this.createdAt + ". Every " + task.frequency + " hour";
		this.createdBy = task.createdBy;
		this.frequency = 0;
		this.rank = task.rank;
		this.status = 0;
	}

	/**
	 * Instantiates a new task.
	 *
	 * @param title
	 *            the title
	 */
	public Task(String title) {
		this.title = title;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the assigned to.
	 *
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * Sets the assigned to.
	 *
	 * @param assignedTo
	 *            the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String[] getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String[] notes) {
		this.notes = notes;
	}

	/**
	 * Gets the feedback.
	 *
	 * @return the feedback
	 */
	public String[] getFeedback() {
		return feedback;
	}

	/**
	 * Sets the feedback.
	 *
	 * @param feedback
	 *            the feedback to set
	 */
	public void setFeedback(String[] feedback) {
		this.feedback = feedback;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * Gets the frequency.
	 *
	 * @return the frequency
	 */

	public int getFrequency() {
		return frequency;
	}

	/**
	 * Sets the frequency.
	 *
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	/**
	 * Gets the rank.
	 *
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Sets the rank.
	 *
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", createdAt=" + createdAt + ", createdBy=" + createdBy
				+ ", assignedTo=" + assignedTo + ", notes=" + Arrays.toString(notes) + ", feedback="
				+ Arrays.toString(feedback) + ", status=" + status + ", frequency=" + frequency + ", rank=" + rank
				+ "]";
	}

		
}