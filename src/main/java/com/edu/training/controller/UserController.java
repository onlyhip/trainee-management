package com.edu.training.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.training.entities.Attendance;
import com.edu.training.entities.ClassAdmin;
import com.edu.training.entities.Course;
import com.edu.training.entities.Fresher;
import com.edu.training.entities.Internship;
import com.edu.training.entities.Score;
import com.edu.training.entities.Status;
import com.edu.training.entities.Trainee;
import com.edu.training.entities.Trainer;
import com.edu.training.entities.TrainingObjective;
import com.edu.training.entities.TypeAttendance;
import com.edu.training.repositories.AttendanceRepository;
import com.edu.training.repositories.ClassAdminRepository;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.FresherRepository;
import com.edu.training.repositories.InternshipRepository;
import com.edu.training.repositories.ScoreRepository;
import com.edu.training.repositories.StatusRepository;
import com.edu.training.repositories.TraineeRepository;
import com.edu.training.repositories.TrainerRepository;
import com.edu.training.repositories.TrainingObjectiveRepository;
import com.edu.training.repositories.UserRepository;
import com.edu.training.services.implementation.CourseServiceImpl;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private ClassAdminRepository classAdminRepository;

	@Autowired
	private TrainerRepository trainerRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private FresherRepository fresherRepository;

	@Autowired
	private InternshipRepository internshipRepository;

	@Autowired
	private TrainingObjectiveRepository toRepository;

	@Autowired
	private TraineeRepository traineeRepository;

	@Autowired
	private ScoreRepository scoreRepository;

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private AttendanceRepository attendRepository;

	@PostConstruct
	public void haha() {
		
	}

	@GetMapping("/")
	public String viewHomePage(Model model) {

		createData();
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

	@RequestMapping(value = "/class-details", method = RequestMethod.GET)
	public String displayClassDetail(Model model, @RequestParam("class-id") int classId) {

		Course course = courseRepository.findById(classId);
		course.setCurrCount(traineeRepository.countCourseByCourseId(course.getId()));
		course.setStatus(
				Timestamp.valueOf(LocalDateTime.now()).compareTo(course.getEndDate()) > 0 ? "Done" : "In Process");
		model.addAttribute("class", course);

		List<Trainee> listTrainee = traineeRepository.findTraineeByCourseId(classId);
		model.addAttribute("trainees", listTrainee);



		return "class-details";
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

	public void createTrainer() {

		Trainer trainer = null;
		Random rand = new Random();
		List<String> hoList = new ArrayList<String>();
		hoList.add("Mai");
		hoList.add("Nguyen");
		hoList.add("Tran");
		hoList.add("Pham");
		hoList.add("Le");
		hoList.add("Cao");
		hoList.add("Phan");
		hoList.add("Do");
		hoList.add("Bui");
		hoList.add("Dang");
		List<String> tenDemList = new ArrayList<String>();
		tenDemList.add("Duy");
		tenDemList.add("Quynh");
		tenDemList.add("Minh");
		tenDemList.add("Cam");
		tenDemList.add("Thu");
		tenDemList.add("Hoang");
		tenDemList.add("Quang");
		List<String> tenList = new ArrayList<String>();
		tenList.add("Thu");
		tenList.add("Tham");
		tenList.add("Toan");

		for (int i = 0; i < 10; i++) {

			trainer = new Trainer();
			String ho = hoList.get(rand.nextInt(7));
			String tenDem = tenDemList.get(rand.nextInt(7));
			String ten = tenList.get(rand.nextInt(3));
			String telephone = "07";
			trainer.setName(ho + " " + tenDem + " " + ten);
			trainer.setAccount(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i));
			trainer.setNational("Viet Nam");
			trainer.setEmail(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i) + "@gmail.com");
			for (int j = 0; j < 7; j++)
				telephone += String.valueOf(rand.nextInt(10));
			trainer.setTelNumber(telephone);
			trainer.setFacebook(ho.substring(0, 1) + tenDem.substring(0, 1) + ten.substring(0, 1) + String.valueOf(i));
			// print all trainee had just created
			trainerRepository.save(trainer);
			System.out.println(trainer);
		}

	}

	public void createCourse() {

		Course course = new Course();
		Random rand = new Random(System.currentTimeMillis());
		Date date1 = new Date();
		Date date2 = new Date();
		List<String> alphabet = new ArrayList<String>();
		alphabet.add("A");
		alphabet.add("B");
		alphabet.add("C");
		alphabet.add("D");
		alphabet.add("E");
		alphabet.add("F");
		int count = 4;
		for (int i = 0; i < 4; i += 2) {
			for (int j = 1; j < 6; j++) {
				course = new Course();
				course.setName(alphabet.get(i) + alphabet.get(j) + String.valueOf(i * 10 + j));
				String sDate = String.valueOf(rand.nextInt(30) + 1) + "/" + String.valueOf(rand.nextInt(6) + 1)
						+ "/2021";
				String eDate = String.valueOf(rand.nextInt(30) + 1) + "/" + String.valueOf(rand.nextInt(6) + 7)
						+ "/2021";
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(eDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				course.setOpenDate(date1);
				course.setEndDate(date2);
				course.setPlanCount(rand.nextInt(5) + 15);
				course.setCurrCount(0);
				course.setTrainer(trainerRepository.getOne(count++));
				courseRepository.save(course);
				System.out.println(course);
			}
		}

	}

	public void createStatus() {

		Random rand = new Random(System.currentTimeMillis());
		Status status = new Status();
		Date date1 = new Date();
		Date date2 = new Date();
		for (int i = 0; i < 30; i++) {
			status = new Status();
			String sDate = String.valueOf(rand.nextInt(30) + 1) + "/" + String.valueOf(rand.nextInt(3) + 1) + "/2021";
			String eDate = String.valueOf(rand.nextInt(30) + 1) + "/" + String.valueOf(rand.nextInt(8) + 4) + "/2021";
			try {
				date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
				date2 = new SimpleDateFormat("dd/MM/yyyy").parse(eDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			status.setType("ahihi");
			status.setStartDay(date1);
			status.setEndDate(date2);
			status.setLearningTime(3);
			statusRepository.save(status);
		}

	}

	public void createFresher() {

		Fresher trainee = null;
		Random rand = new Random(System.currentTimeMillis());
		List<String> hoList = new ArrayList<String>();
		hoList.add("Mai");
		hoList.add("Nguyen");
		hoList.add("Tran");
		hoList.add("Pham");
		hoList.add("Le");
		hoList.add("Cao");
		hoList.add("Phan");
		hoList.add("Do");
		hoList.add("Bui");
		hoList.add("Dang");
		List<String> tenDemList = new ArrayList<String>();
		List<String> tenList = new ArrayList<String>();
		tenList.add("Duy");
		tenList.add("Quynh");
		tenList.add("Minh");
		tenList.add("Cam");
		tenList.add("Thu");
		tenList.add("Hoang");
		tenList.add("Quang");
		tenDemList.add("Duong");
		tenDemList.add("Anh");
		tenDemList.add("Ha");
		tenDemList.add("Thuc");
		tenDemList.add("Teo");
		tenDemList.add("Dang");
		tenDemList.add("Cuc");
		List<String> uniList = new ArrayList<String>();
		uniList.add("FPT");
		uniList.add("FTU");
		uniList.add("BKU");
		uniList.add("UTH");
		uniList.add("RMIT");
		uniList.add("TDT");
		uniList.add("TDM");

		for (int i = 0; i < 100; i++) {
			trainee = new Fresher();
			String ho = hoList.get(rand.nextInt(7));
			String tenDem = tenDemList.get(rand.nextInt(7));
			String ten = tenList.get(rand.nextInt(7));
			String telephone = "08";
			trainee.setName(ho + " " + tenDem + " " + ten);
			trainee.setAccount(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i));
			trainee.setNational("Viet Nam");
			trainee.setEmail(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i) + "@gmail.com");
			for (int j = 0; j < 7; j++)
				telephone += String.valueOf(rand.nextInt(10));
			trainee.setTelNumber(telephone);
			trainee.setFacebook(ho.substring(0, 1) + tenDem.substring(0, 1) + ten.substring(0, 1) + String.valueOf(i));
			trainee.setCourse(courseRepository.getOne(rand.nextInt(10) + 2));
			trainee.setTraineeStatus(statusRepository.getOne(rand.nextInt(30) + 1));
			trainee.setUniversity(uniList.get(rand.nextInt(7)));
			// trainee.setFullTimeWorkingAvailable(fullTimeWorkingAvailable);
			Date graduatedDate = new Date();
			try {
				graduatedDate = new SimpleDateFormat("dd/MM/yyyy").parse("1/1/202" + String.valueOf(rand.nextInt(2)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			trainee.setUniversityGraduationDate(graduatedDate);
			fresherRepository.save(trainee);
		}
	}

	public void createInternship() {
		Internship trainee = null;
		Random rand = new Random(System.currentTimeMillis());
		List<String> hoList = new ArrayList<String>();
		List<String> tenDemList = new ArrayList<String>();
		List<String> tenList = new ArrayList<String>();
		tenList.add("Mai");
		tenList.add("Nguyen");
		tenList.add("Tran");
		tenList.add("Pham");
		tenList.add("Le");
		tenList.add("Cao");
		tenList.add("Phan");
		tenList.add("Do");
		tenList.add("Bui");
		tenList.add("Dang");

		hoList.add("Duy");
		hoList.add("Quynh");
		hoList.add("Minh");
		hoList.add("Cam");
		hoList.add("Thu");
		hoList.add("Hoang");
		hoList.add("Quang");

		tenDemList.add("Duong");
		tenDemList.add("Anh");
		tenDemList.add("Ha");
		tenDemList.add("Thuc");
		tenDemList.add("Teo");
		tenDemList.add("Dang");
		tenDemList.add("Cuc");
		List<String> uniList = new ArrayList<String>();
		uniList.add("FPT");
		uniList.add("FTU");
		uniList.add("BKU");
		uniList.add("UTH");
		uniList.add("RMIT");
		uniList.add("TDT");
		uniList.add("TDM");

		for (int i = 0; i < 50; i++) {
			trainee = new Internship();
			String ho = hoList.get(rand.nextInt(7));
			String tenDem = tenDemList.get(rand.nextInt(7));
			String ten = tenList.get(rand.nextInt(7));
			String telephone = "09";
			trainee.setName(ho + " " + tenDem + " " + ten);
			trainee.setAccount(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i));
			trainee.setNational("Viet Nam");
			trainee.setEmail(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i) + "@gmail.com");
			for (int j = 0; j < 7; j++)
				telephone += String.valueOf(rand.nextInt(10));
			trainee.setTelNumber(telephone);
			trainee.setFacebook(ho.substring(0, 1) + tenDem.substring(0, 1) + ten.substring(0, 1) + String.valueOf(i));
			trainee.setCourse(courseRepository.getOne(rand.nextInt(10) + 2));
			trainee.setTraineeStatus(statusRepository.getOne(rand.nextInt(30) + 1));
			trainee.setUniversity(uniList.get(rand.nextInt(7)));
			internshipRepository.save(trainee);
			System.out.println(trainee);
		}
	}

	public void createTO() {
		TrainingObjective to = null;
		for (int i = 4; i <= 13; i++) {
			to = new TrainingObjective();
			to.setName("ahuhu");
			to.setCode("ACBD");
			to.setTrainer(trainerRepository.getOne(i));
			toRepository.save(to);
			to = new TrainingObjective();
			to.setName("ahehe");
			to.setCode("EFGH");
			to.setTrainer(trainerRepository.getOne(i));
			toRepository.save(to);
		}
	}

	public void createScore() {
		Score score = null;
		// ScoreId scoreId = null;
		Random rand = new Random(System.currentTimeMillis());
		for (Trainee trainee : traineeRepository.findAll()) {
			for (TrainingObjective to : trainee.getCourse().getTrainer().getTrainingObjectives()) {
				score = new Score();
				score.setName("haha");
				System.out.println("To Id: " + to.getId());
				System.out.println("Trainee Id: " + trainee.getId());
				score.setTrainingObjective(toRepository.getOne(to.getId()));
				score.setTrainee(traineeRepository.getOne(trainee.getId()));
				score.setValue(rand.nextInt(6) + 5);
				// scoreRepository.insertScore(trainee.getId(), to.getId(), rand.nextInt(6) + 5,
				// "haha");
				scoreRepository.save(score);

			}
		}
	}

	private void createAttendance() {

		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		Attendance attendance = null;

		for (Trainee trainee : traineeRepository.findAll()) {
			if (Timestamp.valueOf(LocalDateTime.now()).compareTo(trainee.getCourse().getOpenDate()) > 0) {
				start.setTime(trainee.getCourse().getOpenDate());
				if (Timestamp.valueOf(LocalDateTime.now()).compareTo(trainee.getCourse().getEndDate()) > 0)
					end.setTime(trainee.getCourse().getEndDate());
				else
					end.setTime(Timestamp.valueOf(LocalDateTime.now()));
				for (Date date = start.getTime(); start.before(end); start.add(Calendar.DATE,
						7), date = start.getTime()) {
					attendance = new Attendance();
					attendance.setUser(traineeRepository.getOne(trainee.getId()));
					attendance.setDate(date);
					attendance.setType(TypeAttendance.A);
					attendRepository.save(attendance);
				}
			}
		}
	}

	public void createData() {
		// createTrainer();
		// createCourse();
		// createStatus();
		// createFresher();
		// createInternship();
		// createTO();
		// createScore();
		// createAttendance();
	}

}
