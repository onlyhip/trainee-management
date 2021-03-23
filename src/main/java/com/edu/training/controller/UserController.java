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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/")
	public String viewHomePage(Model model) {
		// return findPaginated(1, "firstName", "asc", model);
		
		System.out.println(passwordEncoder.encode("admin"));
		return "index";
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

	@GetMapping("/change-password")
	public String updateUserPasswordForm(Model model) {

		User loginedUser = getLoginedAccount();

		model.addAttribute("user", loginedUser);
		System.out.println("Is password == admin : "
				+ passwordEncoder.matches("admin", loginedUser.getClassAdmin().getPassword()));
		return "change-password";
	}

	@PostMapping("/change-password")
	public String updateUserPassword(@ModelAttribute("user") User user, ModelMap modelMap,
			@RequestParam("oldPassword") String oldPassword,RedirectAttributes attributes) {

		System.out.println(user);
		User loginedUser = getLoginedAccount();
		if (passwordEncoder.matches(oldPassword, loginedUser.getClassAdmin().getPassword()) == false) {
			return "redirect:/change-password?error=true";
		}

		int id = user.getId();

		// get new Class Admin with new Password
		ClassAdmin newClassAdmin = user.getClassAdmin();
		newClassAdmin.setPassword(passwordEncoder.encode(newClassAdmin.getPassword()));
		// get data of old User
		Optional<User> oldUser = userRepository.findById(id);
		User newUser = oldUser.get();

		// change the class Admin
		newUser.setClassAdmin(newClassAdmin);
		newClassAdmin.setUserOTO3(newUser);
		// save to database new ClassAdmin vs new User
		// classAdminRepository.save(newClassAdmin);
		userRepository.save(newUser);

		System.out.println("old Password is correct");

		attributes.addFlashAttribute("result", "success");

		return "redirect:/";
	}

	// @RequestMapping(value = "/class-management", method = RequestMethod.GET)
	// public String displayCourseList(Model model) {

	// return "class-management";
	// }

	@GetMapping("/404")
	public String error() {
		return "404";
	}

	public boolean checkOldPassword(String username, String oldPassword) {
		return userRepository.findPasswordByAccountClassAdmin(username).equals(oldPassword);
	}

	public User getLoginedAccount() {

		String loginedAccount = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = userRepository.findByAccount(loginedAccount);
		User loginedUser = userRepository.getOne(id);
		return loginedUser;
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
