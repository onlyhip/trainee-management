package com.edu.training.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.training.entities.User;
import com.edu.training.repositories.UserRepository;
import com.edu.training.services.core.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// display list of employees
	// @GetMapping("/")
	// public String viewHomePage(Model model) {
	// return findPaginated(1, "firstName", "asc", model);
	// }
	@GetMapping("/")
	public String viewHomePage(Model model) {
		// return findPaginated(1, "firstName", "asc", model);
		return "class-management";
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

	@GetMapping("/updateUser")
	public String updateUser(Model model) {
		
		String loginedAccount =  SecurityContextHolder.getContext().getAuthentication().getName();
		int id = userRepository.findByAccount(loginedAccount);
		User loginedUser = userRepository.getOne(id);

		model.addAttribute("user", loginedUser);

		return "updateUser";
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
