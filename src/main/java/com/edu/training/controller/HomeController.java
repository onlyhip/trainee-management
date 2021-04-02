package com.edu.training.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import com.edu.training.entities.ClassAdmin;
import com.edu.training.entities.Course;
import com.edu.training.entities.Fresher;
import com.edu.training.repositories.ClassAdminRepository;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.FresherRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.repositories.UserRepository;
import com.edu.training.services.implementation.CourseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
	private UserRepository userRepository;

	@Autowired
	private ClassAdminRepository classAdminRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private FresherRepository fresherRepository;

	@Autowired
	private TraineeRepository traineeRepository;

	@Autowired
	private CourseServiceImpl courseService;

	@GetMapping("/")
	public String viewHomePage(Model model) {

		// CreateData.createTrainer();
		// CreateData.createCourse();
		// CreateData.createStatus();
		// CreateData.createFresher();
		// CreateData.createInternship();
		// CreateData.createTO();
		// CreateData.createScore();

		List<Course> listCourse = courseRepository.findAll();
		List<Fresher> listFresher = fresherRepository.findAll();
		int waitingCourse = 0;
		int releaseCourse = 0;
		int runningCourse = 0;
		int waitingFresher = 0;
		int releaseFresher = 0;
		int runningFresher = 0;
		for (Course c : listCourse) {
			c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId()));
			c.setStatus(Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getEndDate()) > 0 ? "Done"
					: Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getOpenDate()) < 0 ? "Waiting" : "In Process");
			if (c.getStatus().equals("Done"))
				releaseCourse++;
			else if (c.getStatus().equals("Waiting"))
				waitingCourse++;
			else
				runningCourse++;
		}

		for (Fresher f : listFresher) {
			if (Timestamp.valueOf(LocalDateTime.now()).compareTo(f.getTraineeStatus().getStartDay()) < 0)
				waitingFresher++;
			else if (Timestamp.valueOf(LocalDateTime.now()).compareTo(f.getTraineeStatus().getEndDate()) > 0)
				releaseFresher++;
			else
				runningFresher++;
		}

		model.addAttribute("totalCourse", listCourse.size());
		model.addAttribute("totalFresher", listFresher.size());
		model.addAttribute("wCourse", waitingCourse);
		model.addAttribute("rCourse", releaseCourse);
		model.addAttribute("rnCourse", runningCourse);
		model.addAttribute("wFresher", waitingFresher);
		model.addAttribute("rFresher", releaseFresher);
		model.addAttribute("rnFresher", runningFresher);

		return "index";
	}

	@RequestMapping(value = "/class-management", method = RequestMethod.GET)
	public String displayCourseList(Model model) {

		int pageSize = 5;

		Page<Course> page = courseService.findPaginated(1, pageSize, "name");
		List<Course> listCourses = page.getContent();
		// listCourses.forEach(c ->
		// c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId())));
		for (Course c : listCourses) {
			c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId()));
			c.setStatus(Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getEndDate()) > 0 ? "Done" : "In Process");
		}
		// listCourses.forEach(c ->
		// c.setStatus(Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getEndDate())
		// > 0 ? "Done" : "In Process"));
		listCourses.forEach(c -> System.out.println(c));
		model.addAttribute("classes", listCourses);

		return "class-management";
	}

	@RequestMapping(value = "/class-management", method = RequestMethod.POST)
	public String displayCourseListByPageSize(Model model, @RequestParam("page-size") int pageSize) {

		Page<Course> page = courseService.findPaginated(1, pageSize, "name");
		List<Course> listCourses = page.getContent();
		for (Course c : listCourses) {
			c.setCurrCount(traineeRepository.countCourseByCourseId(c.getId()));
			c.setStatus(Timestamp.valueOf(LocalDateTime.now()).compareTo(c.getEndDate()) > 0 ? "Done" : "In Process");
		}
		model.addAttribute("classes", listCourses);

		return "class-management";
	}

	// @RequestMapping(value = "/class-details", method = RequestMethod.GET)
	// public String displayClassDetail(Model model, @RequestParam("id") int
	// classId) {

	// Course course = courseRepository.findById(classId);
	// course.setCurrCount(traineeRepository.countCourseByCourseId(course.getId()));
	// course.setStatus(
	// Timestamp.valueOf(LocalDateTime.now()).compareTo(course.getEndDate()) > 0 ?
	// "Done" : "In Process");
	// model.addAttribute("class", course);

	// List<Trainee> listTrainee = traineeRepository.findTraineeByCourseId(classId);
	// model.addAttribute("trainees", listTrainee);

	// return "class-details";
	// }

	@GetMapping("/trainee-management")
	public String displayTraineeManagement() {

		return "trainee-management";
	}

	@GetMapping("/download-templates")
	public String displayDownloadTemplates() {
		return "download-templates";
	}
	

	@GetMapping("/general-management")
	public String displayTrainerList() {
		return "trainer-list";
	}

	// @GetMapping("/page/{pageNo}")
	// public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
	// @RequestParam("sortField") String sortField,
	// @RequestParam("sortDir") String sortDir, Model model) {
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
}
