package com.edu.training.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.edu.training.entities.Course;
import com.edu.training.entities.Fresher;
import com.edu.training.entities.Internship;
import com.edu.training.entities.Score;
import com.edu.training.entities.Status;
import com.edu.training.entities.Trainer;
import com.edu.training.entities.TrainingObjective;

public class test {

	public static void createFresher() {

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
			String telephone = "";
			trainee.setName(ho + " " + tenDem + " " + ten);
			trainee.setAccount(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i));
			trainee.setNational("Viet Nam");
			trainee.setEmail(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i) + "@gmail.com");
			for (int j = 0; j < 9; j++)
				telephone += String.valueOf(rand.nextInt(10));
			trainee.setTelNumber(telephone);
			trainee.setFacebook(ho.substring(0, 1) + tenDem.substring(0, 1) + ten.substring(0, 1) + String.valueOf(i));
			// trainee.setCourse(traineeRepository.getOne(rand.nextInt(15) + 14));
			// trainee.setTraineeStatus(statusRepository.getOne(rand.nextInt(30) + 30));
			trainee.setUniversity(uniList.get(rand.nextInt(7)));
			System.out.println(trainee);
		}
	}

	public static void createInternship() {
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

		for (int i = 0; i < 100; i++) {
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
			// trainee.setCourse(traineeRepository.getOne(rand.nextInt(15) + 14));
			// trainee.setTraineeStatus(statusRepository.getOne(rand.nextInt(30) + 30));
			trainee.setUniversity(uniList.get(rand.nextInt(7)));
			System.out.println(trainee);
		}
	}

	public static void createStatus() {

		Random rand = new Random(System.currentTimeMillis());
		Status status = new Status();
		Date date1 = new Date();
		Date date2 = new Date();
		for (int i = 0; i < 30; i++) {
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
			System.out.println(status);
		}

	}

	public static void createCourse() {

		Course course = new Course();
		Random rand = new Random();
		Date date1 = new Date();
		Date date2 = new Date();
		List<String> alphabet = new ArrayList<String>();
		alphabet.add("A");
		alphabet.add("B");
		alphabet.add("C");
		alphabet.add("D");
		alphabet.add("E");
		alphabet.add("F");
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 6; j++) {
				course.setName(alphabet.get(i) + alphabet.get(j) + String.valueOf(i * 10 + j));
				String sDate = String.valueOf(rand.nextInt(30) + 1) + "/" + String.valueOf(rand.nextInt(6) + 1)
						+ "/2021";
				String eDate = String.valueOf(rand.nextInt(30) + 1) + "/" + String.valueOf(rand.nextInt(6) + 7)
						+ "/2022";
				try {
					date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
					date2 = new SimpleDateFormat("dd/MM/yyyy").parse(eDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				course.setOpenDate(date1);
				course.setEndDate(date2);
				course.setPlanCount(rand.nextInt(5) + 15);
				course.setTrainer(new Trainer(rand.nextInt(10) + 3));
				System.out.println(course);
			}
		}

	}

	public static void createTrainer() {

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
			String telephone = "";
			trainer.setName(ho + " " + tenDem + " " + ten);
			trainer.setAccount(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i));
			trainer.setNational("Viet Nam");
			trainer.setEmail(ten + ho.charAt(0) + ten.charAt(0) + String.valueOf(i) + "@gmail.com");
			for (int j = 0; j < 9; j++)
				telephone += String.valueOf(rand.nextInt(10));
			trainer.setTelNumber(telephone);
			trainer.setFacebook(ho.substring(0, 1) + tenDem.substring(0, 1) + ten.substring(0, 1) + String.valueOf(i));
			// print all trainee had just created
			System.out.println(trainer);
		}

	}

	public static void createTO() {
		TrainingObjective to = null;
		for (int i = 4; i <= 13; i++) {
			to = new TrainingObjective();
			to.setName("ahuhu");
			to.setCode("ACBD");
			// to.setTrainer(trainerRepository.getOne(i));
			to = new TrainingObjective();
			to.setName("ahehe");
			to.setCode("EFGH");
			// to.setTrainer(trainerRepository.getOne(i));
		}

	}

	public static void createScore() {

		Score score = new Score();

	}

	public static void main(String[] args) {
		// createTrainer();
		// createCourse();
		// createFresher();
		// createStatus();
		// createInternship();
	}
}
