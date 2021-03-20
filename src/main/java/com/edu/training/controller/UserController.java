package com.edu.training.controller;


import java.util.List;

import com.edu.training.entities.User;
import com.edu.training.services.core.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
   
    @Autowired
    private UserService userService;

    // display list of employees
	// @GetMapping("/")
	// public String viewHomePage(Model model) {
	// 	return findPaginated(1, "firstName", "asc", model);		
	// }
	@GetMapping("/")
	public String viewHomePage(Model model) {
		// return findPaginated(1, "firstName", "asc", model);
		return "class-management";		
	}
	
	// @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
	// public String showLoginForm(Model model) {
	// 	return "login_page";
	// }

	// @GetMapping("/showNewUserForm")
	// public String showNewEmployeeForm(Model model) {
	// 	// create model attribute to bind form data
	// 	User user = new User();
	// 	model.addAttribute("user", user);
	// 	return "new_user";
	// }
	
	// @PostMapping("/saveUser")
	// public String saveEmployee(@ModelAttribute("user") User user) {
	// 	// save employee to database
	// 	userService.save(user);
	// 	return "redirect:/";
	// }
	
	// @GetMapping("/showFormForUpdate/{id}")
	// public String showFormForUpdate(@PathVariable ( value = "id") long id, Model model) {
		
	// 	// get employee from the service
	// 	User user = userService.findById(id);
		
	// 	// set employee as a model attribute to pre-populate the form
	// 	model.addAttribute("user", user);
	// 	return "update_user";
	// }
	
	// @GetMapping("/deleteUser/{id}")
	// public String deleteUser(@PathVariable (value = "id") long id) {
    //     userService.delete(id);
    //     return "redirect:/";
	// }
	
	
	// @GetMapping("/page/{pageNo}")
	// public String findPaginated(@PathVariable (value = "pageNo") int pageNo, 
	// 		@RequestParam("sortField") String sortField,
	// 		@RequestParam("sortDir") String sortDir,
	// 		Model model) {
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

