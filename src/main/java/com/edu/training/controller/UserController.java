package com.edu.training.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.training.entities.ClassAdmin;
import com.edu.training.entities.User;
import com.edu.training.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired 
    private PasswordEncoder passwordEncoder;

	// display list of employees
	// @GetMapping("/")
	// public String viewHomePage(Model model) {
	// return findPaginated(1, "firstName", "asc", model);
	// }
	@GetMapping("/")
	public String viewHomePage(Model model) {
		// return findPaginated(1, "firstName", "asc", model);
		System.out.println(passwordEncoder.encode("admin"));
		return "login";
	}

	@GetMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@GetMapping("/changePassword")
	public String updateUserPasswordForm(Model model) {
		
		String loginedAccount =  SecurityContextHolder.getContext().getAuthentication().getName();
		int id = userRepository.findByAccount(loginedAccount);
		User loginedUser = userRepository.getOne(id);

		model.addAttribute("user", loginedUser);

		return "change-password";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String updateUserPassword(@ModelAttribute("user") User user, ModelMap ModelMap) {
		
		int id = user.getId();
		
		// get new Class Admin with new Password 
		ClassAdmin newClassAdmin = user.getClassAdmin();
		newClassAdmin.setPassword(passwordEncoder.encode(newClassAdmin.getPassword()));
		// get data of old User
		System.out.println("Password decode: " + passwordEncoder.toString());
		Optional<User> oldUser = userRepository.findById(id);
		User newUser = oldUser.get();
		
		// change the class Admin
		newUser.setClassAdmin(newClassAdmin);
		newClassAdmin.setUserOTO3(newUser);
		// save to database new ClassAdmin vs new User
		// classAdminRepository.save(newClassAdmin);
		userRepository.save(newUser);

		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "index";
	}

	@GetMapping("/404")
	public String error() {
		return "404";
	}

	// @GetMapping("/showNewUserForm")
	// public String showNewEmployeeForm(Model model) {
	// // create model attribute to bind form data
	// User user = new User();
	// model.addAttribute("user", user);
	// return "new_user";
	// }

	// @PostMapping("/saveUser")
	// public String saveEmployee(@ModelAttribute("user") User user) {
	// // save employee to database
	// userService.save(user);
	// return "redirect:/";
	// }

	// @GetMapping("/showFormForUpdate/{id}")
	// public String showFormForUpdate(@PathVariable ( value = "id") long id, Model
	// model) {

	// // get employee from the service
	// User user = userService.findById(id);

	// // set employee as a model attribute to pre-populate the form
	// model.addAttribute("user", user);
	// return "update_user";
	// }

	// @GetMapping("/deleteUser/{id}")
	// public String deleteUser(@PathVariable (value = "id") long id) {
	// userService.delete(id);
	// return "redirect:/";
	// }

	// @GetMapping("/page/{pageNo}")
	// public String findPaginated(@PathVariable (value = "pageNo") int pageNo,
	// @RequestParam("sortField") String sortField,
	// @RequestParam("sortDir") String sortDir,
	// Model model) {
	// int pageSize = 5;

	// Page<User> page = userService.findPaginated(pageNo, pageSize, sortField,
	// sortDir);
	// List<User> listUsers = page.getContent();

	// model.addAttribute("currentPage", pageNo);
	// model.addAttribute("totalPages", page.getTotalPages());
	// model.addAttribute("totalItems", page.getTotalElements());

	// model.addAttribute("sortField", sortField);
	// model.addAttribute("sortDir", sortDir);
	// model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	// model.addAttribute("listUsers", listUsers);
	// return "index";
	// }
}
