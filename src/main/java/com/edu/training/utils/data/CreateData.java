package com.edu.training.utils.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.edu.training.entities.Course;
import com.edu.training.entities.Fresher;
import com.edu.training.entities.Trainer;
import com.edu.training.entities.Internship;
import com.edu.training.entities.Status;
import com.edu.training.entities.TrainingObjective;
import com.edu.training.repositories.CourseRepository;
import com.edu.training.repositories.FresherRepository;
import com.edu.training.repositories.InternshipRepository;
import com.edu.training.repositories.ScoreRepository;
import com.edu.training.repositories.StatusRepository;
import com.edu.training.repositories.TrainerRepository;
import com.edu.training.repositories.TrainingObjectiveRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CreateData {

    @Autowired
    private static TrainerRepository trainerRepository;

    @Autowired
    private static CourseRepository courseRepository;

    @Autowired
    private static StatusRepository statusRepository;

    @Autowired
    private static FresherRepository fresherRepository;

    @Autowired
    private static InternshipRepository internshipRepository;

    @Autowired
    private static TrainingObjectiveRepository toRepository;

    @Autowired
    private static ScoreRepository scoreRepository;

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

    public static void createCourse() {

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
                course.setCurrCount(0);
                course.setTrainer(trainerRepository.getOne(rand.nextInt(10) + 4));
                courseRepository.save(course);
                System.out.println(course);
            }
        }

    }

    public static void createStatus() {

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

    public static void createTO() {
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

    public static void createScore() {
        // Score score = null;
        // ScoreId scoreId = null;
        Random rand = new Random(System.currentTimeMillis());
        for (TrainingObjective to : toRepository.findAll()) {
            for (Course course : to.getTrainer().getCourse()) {
                for (Trainee trainee : course.getTrainee()) {
                    score = new Score();
                    score.setName("haha");
                    score.setTrainingObjective(toRepository.getOne(to.getId()));
                    score.setTrainee(traineeRepository.getOne(trainee.getId()));
                    score.setValue(rand.nextInt(6) + 5);
                    scoreRepository.save(score);
                    // scoreRepository.insertScore(trainee.getId(), to.getId(), rand.nextInt(6) + 5, "haha");
                }
            }
        }
    }

}
