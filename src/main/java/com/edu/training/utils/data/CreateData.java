package com.edu.training.utils.data;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.edu.training.entities.*;
import com.edu.training.repositories.*;

public class CreateData {

    public void createTrainer(TrainerRepository trainerRepository) {
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

    public void createCourse(TrainerRepository trainerRepository, CourseRepository courseRepository) {

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
        for (int i = 0; i < 6; i += 2) {
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
                course.setTrainer(trainerRepository.getOne(rand.nextInt(10) + 4));
                courseRepository.save(course);
                System.out.println(course);
            }
        }

    }

    public void createStatus(StatusRepository statusRepository) {

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

    public void createFresher(CourseRepository courseRepository, StatusRepository statusRepository,
            FresherRepository fresherRepository) {

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
            trainee.setCourse(courseRepository.getOne(rand.nextInt(15) + 1));
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

    public void createInternship(CourseRepository courseRepository, StatusRepository statusRepository,
            InternshipRepository internshipRepository) {
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
            trainee.setCourse(courseRepository.getOne(rand.nextInt(15) + 1));
            trainee.setTraineeStatus(statusRepository.getOne(rand.nextInt(30) + 1));
            trainee.setUniversity(uniList.get(rand.nextInt(7)));
            internshipRepository.save(trainee);
            System.out.println(trainee);
        }
    }

    public void createTO(TrainerRepository trainerRepository, TrainingObjectiveRepository toRepository,
            CourseRepository courseRepository) {
        TrainingObjective to = null;
        byte[] array = new byte[4]; // length is bounded by 7
        
        List<String> nameScore = new ArrayList<>();
        nameScore.add("SQL");
        nameScore.add("Java SE");
        nameScore.add("Java EE");
        nameScore.add("JavaScript");
        nameScore.add("Hibernate");
        nameScore.add("Angular");
        nameScore.add("React");
        nameScore.add("Python");
        nameScore.add("Mock Project");
        nameScore.add("Front End");
        nameScore.add("Java Web Back End");
        nameScore.add("C++");
        nameScore.add("Automated tester");
        nameScore.add("BA Analyes");
        nameScore.add("Kotlin Android");
        Random rand = new Random(System.currentTimeMillis());
        for(int i = 0; i < 30; i++) {
            
            to = new TrainingObjective();
            String name = nameScore.get(i/2);
            to.setName(name);
            if(i % 2 == 0) {
                to.setTrainer(trainerRepository.getOne(rand.nextInt(5) + 4));
            } else {
                to.setTrainer(trainerRepository.getOne(rand.nextInt(5) + 9));
            }
            new Random().nextBytes(array);
            String code = new String(array, Charset.forName("UTF-8")).toUpperCase();
            to.setCode(code);
            toRepository.save(to);
        }

    }

    public void createScore(CourseRepository courseRepository, ScoreRepository scoreRepository,
            TrainingObjectiveRepository toRepository, TraineeRepository traineeRepository) {

        Random rand = new Random(System.currentTimeMillis());
        Score score = null;
        for (Course course : courseRepository.findAll()) {
            if (course.getTrainee() != null) {
                for (Trainee trainee : course.getTrainee()) {
                    for (TrainingObjective to : course.getTrainer().getTrainingObjectives()) {
                        score = new Score();
                        String name = to.getName();
                        score.setName(name);
                        score.setTrainingObjective(toRepository.getOne(to.getId()));
                        score.setTrainee(traineeRepository.getOne(trainee.getId()));
                        score.setValue(rand.nextInt(6) + 5);
                        scoreRepository.save(score);
                    }
                }
            }
        }
    }

    public void createAttendance(TraineeRepository traineeRepository, AttendanceRepository attendanceRepository) {

        Attendance att = null;
        Random rand = new Random(System.currentTimeMillis());

        for (Trainee trainee : traineeRepository.findAll()) {

            LocalDate startDate = trainee.getCourse().getOpenDate().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDate();
            LocalDate endDate = trainee.getCourse().getEndDate().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDate();

            for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(20)) {
                if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY))
                    date = date.plusDays(1);
                att = new Attendance();
                att.setType(CONSTRAINT.TYPE_ATTENDANCE.get(rand.nextInt(7)));
                att.setUser(traineeRepository.getOne(trainee.getId()));
                att.setDate(java.sql.Date.valueOf(date));
                attendanceRepository.save(att);
            }

        }

    }

}
