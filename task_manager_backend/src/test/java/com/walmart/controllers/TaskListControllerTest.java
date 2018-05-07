/*
 * @author Shruti Harihar
 */
package com.walmart.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.walmart.tasklist.TaskListApplication;
import com.walmart.tasklist.models.Task;
import com.walmart.tasklist.repositories.TaskRepository;

/**
 * The Class TaskListControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TaskListApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TaskListControllerTest {

	/** The mock mvc. */
	private MockMvc mockMvc;

	/** The wac. */
	@Autowired
	private WebApplicationContext wac;

	@MockBean
	private TaskRepository taskRepository;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}

	/**
	 * Verify all to do list.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void verifyAllToDoList() throws Exception {
		List<Task> tasks = new ArrayList<Task>();
		Task task = new Task("Title");
		tasks.add(task);
		given(taskRepository.findAll()).willReturn(tasks);
		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/tasks").accept(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.*", hasSize(1))).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	/**
	 * Verify to do by id.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void verifyToDoById() throws Exception {
		given(taskRepository.findById("4")).willReturn(Optional.of(new Task("test title")));
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/tasks/4")).andReturn()
				.getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

	}

	/**
	 * Verify invalid to do argument.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void verifyInvalidToDoId() throws Exception {
		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.get("/api/ tasks/id").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	/**
	 * Verify delete to do.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void verifyDeleteToDo() throws Exception {

		given(taskRepository.findById("4")).willReturn(Optional.of(mock(Task.class)));
		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/tasks/4").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	/**
	 * Verify invalid to do id to delete.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void verifyInvalidToDoIdToDelete() throws Exception {
		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.delete("/api/tasks/4").accept(MediaType.APPLICATION_JSON))
				.andDo(print()).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

}
