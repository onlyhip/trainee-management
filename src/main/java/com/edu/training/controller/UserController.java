package com.edu.training.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.training.entities.Attendance;
import com.edu.training.entities.ClassAdmin;
import com.edu.training.repositories.ClassAdminRepository;
import com.edu.training.repositories.UserRepository;
import com.edu.training.services.implementation.UserServiceImpl;

import org.aspectj.lang.annotation.Before;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClassAdminRepository classAdminRepository;

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
		ClassAdmin loginedAdmin = classAdminRepository.getLoginedAccount();
		model.addAttribute("user", loginedAdmin);

		return "change-password";
	}

	@PostMapping("/change-password")
	public String updateUserPassword(ModelMap modelMap, @RequestParam("new-password") String newPassword,
			@RequestParam("oldPassword") String oldPassword, RedirectAttributes attributes) {

		ClassAdmin loginedAdmin = classAdminRepository.getLoginedAccount();
		if (passwordEncoder.matches(oldPassword, loginedAdmin.getPassword()) == false) {
			return "redirect:/change-password?error=true";
		}

		// get new Class Admin with new Password
		loginedAdmin.setPassword(passwordEncoder.encode(newPassword));

		classAdminRepository.save(loginedAdmin);

		attributes.addFlashAttribute("result", "success");

		return "redirect:/";
	}

}
