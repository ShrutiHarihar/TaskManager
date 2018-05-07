/*
 * @author Shruti Harihar
 */
package com.walmart.tasklist.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.tasklist.models.User;
import com.walmart.tasklist.repositories.UserRepository;


/**
 * The Class UserController.
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	/** The user repository. */
	@Autowired
	UserRepository userRepository;

	/**
	 * Creates the todo.
	 *
	 * @param user the user
	 * @return the user
	 */
	@PostMapping("/login")
	public User loginUser(@Valid @RequestBody User user) {
		User byusername = userRepository.findByUsername(user.getUsername());
		if (byusername != null) {
			if (byusername.getPassword().equals(user.getPassword())) {
				return byusername;
			}
		}

		return new User();
	}

	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	@PostMapping("/register")
	public User createUser(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	/**
	 * Gets the all employees.
	 *
	 * @return the all employees
	 */
	@GetMapping("/employees")
	public List<User> getAllEmployees() {
		return userRepository.findByRole("Employee");
	}

}
/*
 * public void login(HttpServletRequest request, HttpServletResponse response) {
 * 
 * HttpSession session = request.getSession(); User valid_user = new User();
 * Query query = new Query(Criteria.where("username").is("users")); List<User>
 * users = mongoTemplate.find(query, User.class); Iterator it =
 * users.iterator(); if(it.hasNext()) { valid_user = (User) it.next(); }
 * 
 * // User valid_user = mongoOperations.findByUsername((String) //
 * request.getAttribute("username"));
 * 
 * try { if (valid_user.getPassword().equals("passs")) {
 * session.setAttribute("user", valid_user); response.setStatus(200);
 * response.getOutputStream().print(valid_user.getRoles());
 * response.getOutputStream().print(valid_user.getId());
 * 
 * } else response.sendError(401, "Unauthorized access"); } catch (IOException
 * e) {
 * 
 * e.printStackTrace(); }
 * 
 * }
 */