package com.edu.training.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.training.entities.ClassAdmin;
import com.edu.training.repositories.ClassAdminRepository;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClassAdminRepository classAdminRepository;

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

		ClassAdmin loginedAdmin = getLoginedAccount();

		model.addAttribute("user", loginedAdmin);
		return "change-password";
	}

	@PostMapping("/change-password")
	public String updateUserPassword(ModelMap modelMap, @RequestParam("new-password") String newPassword,
			@RequestParam("oldPassword") String oldPassword, RedirectAttributes attributes) {

		ClassAdmin loginedAdmin = getLoginedAccount();
		if (passwordEncoder.matches(oldPassword, loginedAdmin.getPassword()) == false) {
			return "redirect:/change-password?error=true";
		}

		// get new Class Admin with new Password
		loginedAdmin.setPassword(passwordEncoder.encode(newPassword));

		classAdminRepository.save(loginedAdmin);

		attributes.addFlashAttribute("result", "success");

		return "redirect:/";
	}

	@RequestMapping(value = "/class-management", method = RequestMethod.GET)
	public String displayCourseList(Model model) {

		return "class-management";
	}

	@GetMapping("/404")
	public String error() {
		return "404";
	}

	public boolean checkOldPassword(String username, String oldPassword) {
		return userRepository.findPasswordByAccountClassAdmin(username).equals(oldPassword);
	}

	public ClassAdmin getLoginedAccount() {

		String loginedAccount = SecurityContextHolder.getContext().getAuthentication().getName();
		int id = classAdminRepository.findIdByAccount(loginedAccount);
		ClassAdmin loginedUser = classAdminRepository.getOne(id);
		return loginedUser;
	}

	// @GetMapping("/page/{pageNo}")
	// public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
	// 		@RequestParam("sortDir") String sortDir, Model model) {
	// 	int pageSize = 5;

	// 	Page<User> page = userService.findPaginated(pageNo, pageSize, sortField, sortDir);
	// 	List<User> listUsers = page.getContent();

	// 	model.addAttribute("currentPage", pageNo);
	// 	model.addAttribute("totalPages", page.getTotalPages());
	// 	model.addAttribute("totalItems", page.getTotalElements());

	// 	model.addAttribute("sortField", sortField);
	// 	model.addAttribute("sortDir", sortDir);
	// 	model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	// 	model.addAttribute("listUsers", listUsers);
	// 	return "index";
	// }
}
