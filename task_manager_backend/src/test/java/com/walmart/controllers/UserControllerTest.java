/*
 * @author Shruti Harihar
 */
package com.walmart.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

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
import com.walmart.tasklist.models.User;
import com.walmart.tasklist.repositories.UserRepository;

/**
 * The Class TaskListControllerTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TaskListApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {

	/** The mock mvc. */
	private MockMvc mockMvc;

	/** The wac. */
	@Autowired
	private WebApplicationContext wac;

	@MockBean
	private UserRepository userRepository;

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
	public void verifyLogin() throws Exception {

		given(userRepository.findByUsername("username")).willReturn(new User("username", "password", "role"));

		MockHttpServletResponse response = mockMvc
				.perform(MockMvcRequestBuilders.post("/user/login").contentType(MediaType.APPLICATION_JSON)
						.content("{\"role\": \"\", \"username\": \"username\", \"password\": \"password\"}"))
				.andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
	}

	@Test
	public void verifyLoginWithWrongContent() throws Exception {

		given(userRepository.findByUsername("username")).willReturn(new User("username", "password", "role"));

		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
				.content("{\"role\": \"\", \"username\": \"username\", \"password\": \"wrongpassword\"}")
				.accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
	}
}
