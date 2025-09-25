package edu.ccrm.cli;

import edu.ccrm.config.AppConfig;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.instructor;
import edu.ccrm.domain.semester;
import edu.ccrm.domain.student;

import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;
import edu.ccrm.io.BackupService;
import edu.ccrm.io.ImportExportService;
import edu.ccrm.service.*;
import edu.ccrm.utils.RecursiveUtils;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class MainMenu {


    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final TranscriptService transcriptService;
    private final ImportExportService importExportService;
    private final BackupService backupService;


    private final List<instructor> instructors = new ArrayList<>();
    private int nextInstructorId = 1;


    private final Scanner scanner;

    public MainMenu() {
        this.studentService = new StudentService();
        this.courseService = new CourseService();
        this.enrollmentService = new EnrollmentService(studentService, courseService);
        this.transcriptService = new TranscriptService();
        this.importExportService = new ImportExportService();
        this.backupService = new BackupService();
        this.scanner = new Scanner(System.in);


        seedInitialData();
    }


    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    handleStudentManagement();
                    break;
                case 2:
                    handleCourseManagement();
                    break;
                case 3:
                    handleEnrollment();
                    break;
                case 4:
                    handleTranscript();
                    break;
                case 5:
                    handleImportExport();
                    break;
                case 6:
                    handleBackup();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Thank you for using CCRM. Goodbye!");
    }


    private void displayMenu() {
        System.out.println("\n--- Campus Course & Records Manager (CCRM) ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Enroll Student in Course");
        System.out.println("4. View Student Transcript");
        System.out.println("5. Import/Export Data");
        System.out.println("6. Backup Data");
        System.out.println("0. Exit");
        System.out.println("----------------------------------------------------------");
    }



    private void handleStudentManagement() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println(("3. search student"));
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("Enter full name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter registration number: ");
            String regNo = scanner.nextLine();
            studentService.addStudent(name, email, regNo);
        } else if (choice == 2) {
            List<student> students = studentService.getAllStudents();
            System.out.println("\n--- All Students ---");
            for (student student : students) {
                System.out.println(student);
            }
        } else if (choice == 3) {

            System.out.println("enter registration number to search student: ");
            String regNo = scanner.nextLine();
            student foundStudent = studentService.getStudentByRegNo(regNo);
            if(foundStudent!= null)
            {
                System.out.println("student found: "+ foundStudent);

            }
            else {
                System.out.println("error: student not found!!!!");
            }



        }
    }

    private void handleCourseManagement() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add New Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Add New Instructor");
        System.out.println("4. Assign Instructor to Course");
        System.out.println("5. search course by course id");
        System.out.println("6. Assign semester to course");
        System.out.print("Enter your choice: ");
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                addCourse();
                break;
            case 2:
                viewAllCourses();
                break;
            case 3:
                addInstructor();
                break;
            case 4:
                assignInstructor();
                break;
            case 5:
                System.out.println("Enter course code to search: ");
                String code = scanner.nextLine();
                Course foundCourse = courseService.getCourseByCode(code);
                if(foundCourse!= null)
                {
                    System.out.println("student found: "+ foundCourse);

                }
                else {
                    System.out.println("error: course not found!!!!");
                }
                break;
            case 6:
                assignSem();


            default:
                System.out.println("Invalid choice.");
                break;
        }
    }
    private void assignSem()
    {
        semester sem1 = semester.Fall;
        semester sem2 = semester.Spring;
        semester sem3 = semester.Summer;
        semester sem4 = semester.Winter;
        System.out.println("enter course code to proceed with: ");
        String code = scanner.nextLine();
        System.out.println("choose semester: ");
        System.out.println("----------------------------------------------------------");
        System.out.println("1. Fall semester");
        System.out.println("2. Spring semester");
        System.out.println("3. Summer semester");
        System.out.println("4. Winter semester");
        System.out.println("----------------------------------------------------------");
        int choice = Integer.parseInt(scanner.nextLine());


        switch (choice){
            case 1:
                courseService.updateSemester(code, sem1);
                break;
            case 2:
                courseService.updateSemester(code, sem2);
                break;
            case 3:
                courseService.updateSemester(code, sem3);
                break;
            case 4:
                courseService.updateSemester(code, sem4);
                break;
            default:
                System.out.println("invalid choice");
                break;
        }
    }
    private void addCourse() {
        try {
            System.out.print("Enter course code (e.g., CS101): ");
            String code = scanner.nextLine();
            System.out.print("Enter course title: ");
            String title = scanner.nextLine();
            System.out.print("Enter credits: ");
            int credits = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter department: ");
            String department = scanner.nextLine();

            Course newCourse = new Course.CourseBuilder(code, title)
                    .withCredits(credits)
                    .withDepartment(department)
                    .build();

            courseService.addCourse(newCourse);
            List<Course> courses = courseService.getAllCourse();
            courses.add(newCourse);

        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid number format for credits.");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void viewAllCourses() {
        List<Course> courses = courseService.getAllCourse();
        System.out.println("\n--- All Courses ---");
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            for (Course course : courses) {
                System.out.println(course);
            }
        }
    }

    private void addInstructor() {
        System.out.println("\n--- Add New Instructor ---");
        System.out.print("Enter instructor's full name: ");
        String name = scanner.nextLine();
        System.out.print("Enter instructor's email: ");
        String email = scanner.nextLine();
        System.out.print("Enter instructor's department: ");
        String department = scanner.nextLine();
        System.out.print("Enter instructor's specialization: ");
        String specialization = scanner.nextLine();

        instructor newInstructor = new instructor(nextInstructorId++, name, email, department,specialization);
        instructors.add(newInstructor);
        System.out.println("SUCCESS: Instructor added -> " + newInstructor);
    }

    private void assignInstructor() {
        System.out.println("\n--- Assign Instructor to Course ---");
        viewAllCourses();
        System.out.print("Enter the course code to assign an instructor to: ");
        String courseCode = scanner.nextLine();

        if (instructors.isEmpty()) {
            System.out.println("No instructors available. Please add an instructor first.");
            return;
        }

        System.out.println("\n--- Available Instructors ---");
        for (instructor instructor : instructors) {
            System.out.println(instructor);
        }
        System.out.print("Enter the ID of the instructor to assign: ");
        int instructorId = Integer.parseInt(scanner.nextLine());

        instructor selectedInstructor = null;
        for (instructor instructor : instructors) {
            if (instructor.getId() == instructorId) {
                selectedInstructor = instructor;
                break;
            }
        }

        if (selectedInstructor != null) {
            courseService.assignInstructorToCourse(courseCode, selectedInstructor);
        } else {
            System.out.println("ERROR: Instructor with ID " + instructorId + " not found.");
        }
    }

    private void handleEnrollment() {
        System.out.println("\n--- Enroll Student ---");
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter course code (e.g., CS101): ");
        String courseCode = scanner.nextLine();

        try {
            enrollmentService.enrollStudentInCourse(studentId, courseCode);
        } catch (DuplicateEnrollmentException | MaxCreditLimitExceededException e) {
            System.err.println("ERROR: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("ERROR: Invalid input - " + e.getMessage());
        }
    }

    private void handleTranscript() {
        System.out.println("\n--- Generate Transcript ---");
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        student student = studentService.getStudentById(studentId);
        if (student != null) {
            String transcript = transcriptService.generateTranscript(student);
            System.out.println(transcript);
        } else {
            System.out.println("ERROR: Student not found.");
        }
    }

    private void handleImportExport() {
        AppConfig config = AppConfig.getInstance();
        System.out.println("\n--- Import/Export ---");
        System.out.println("1. Import Students from CSV");
        System.out.println("2. Export All Data to CSV");
        System.out.print("Enter your choice: ");
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            List<student> importedStudents = importExportService.importStudentsFromCSV(config.getStudentImportFilePath());
            for (student s : importedStudents) {
                studentService.addStudent(s.getName(), s.getEmail(), s.getRegno());
            }
        } else if (choice == 2) {
            importExportService.exportData(

                    config.getStudentExportFilePath(),
                    config.getCourseExportFilePath(),
                    studentService,
                    courseService
            );
        }
    }

    private void handleBackup() {
        AppConfig config = AppConfig.getInstance();
        System.out.println("\n--- Backup Management ---");
        backupService.createBackup(config.getExportFolderPath(), config.getBackupFolderPath());

        System.out.println("Calculating total size of all backups...");
        long size = RecursiveUtils.calculateDirectorySize(Paths.get(config.getBackupFolderPath()));
        System.out.println("Total backup directory size: " + size / 1024 + " KB");
    }


    private void seedInitialData() {

        studentService.addStudent("Alice Johnson", "alice@example.com", "S001");
        studentService.addStudent("Bob Williams", "bob@example.com", "S002");


        instructor profSmith = new instructor(nextInstructorId++, "Dr. Smith", "smith@campus.edu", "Computer Science", "Computer Science");
        instructors.add(profSmith);


        Course cs101 = new Course.CourseBuilder("CS101", "Intro to Programming")
                .withCredits(3)
                .withDepartment("Computer Science")
                .withInstructor(profSmith)
                .build();
        Course math101 = new Course.CourseBuilder("MATH101", "Calculus I")
                .withCredits(4)
                .withDepartment("Mathematics")
                .build();
        courseService.addCourse(cs101);
        courseService.addCourse(math101);
        System.out.println("INFO: Initial dummy data loaded.");
    }
}

