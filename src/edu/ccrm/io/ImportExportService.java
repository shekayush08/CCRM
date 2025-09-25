package edu.ccrm.io;
import edu.ccrm.domain.Course;
import edu.ccrm.domain.student;
import edu.ccrm.service.CourseService;
import edu.ccrm.service.StudentService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ImportExportService {
    public ImportExportService(){}


    public List<student> importStudentsFromCSV(String filePath) {
        List<student> importedStudents = new ArrayList<>();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {

                String[] parts = line.split(",");
                if (parts.length >= 3) {

                    student student = new student(0, parts[1].trim(), parts[2].trim(), parts[0].trim());
                    importedStudents.add(student);
                }
            });
            System.out.println("SUCCESS: Successfully read " + importedStudents.size() + " students from " + filePath);
        } catch (IOException e) {
            System.err.println("ERROR: Failed to read student data from file: " + e.getMessage());
        }
        return importedStudents;
    }
    public void exportData(String studenfilePath, String coursefilePath, StudentService studentService, CourseService courseService)
    {
        exportStudents(studenfilePath, studentService.getAllStudents());
        exportCourses(coursefilePath, courseService.getAllCourse());
    }

    private void exportStudents(String filePath, List<student> students) {
        System.out.println("INFO: Starting student export to: " + filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {

            writer.write("ID,RegNo,FullName,Email,Status\n");

            for (student student : students) {
                String line = String.format("%d,%s,%s,%s,%s\n",
                        student.getId(),
                        student.getRegno(),
                        student.getName(),
                        student.getEmail(),
                        student.getStatus());
                writer.write(line);
            }
            System.out.println("INFO: Student export finished successfully.");
        } catch (IOException e) {
            System.err.println("FATAL: Failed to write to student export file: " + e.getMessage());
        }
    }
    private void exportCourses(String filePath, List<Course> courses) {
        System.out.println("INFO: Starting course export to: " + filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {

            writer.write("Code,Title,Credits,Department,Semester,Instructor\n");

            for (Course course : courses) {

                String instructorName = (course.getInstructor() != null) ? course.getInstructor().getName() : "N/A";
                String line = String.format("%s,%s,%d,%s,%s,%s\n",
                        course.getCode(),
                        course.getTitle(),
                        course.getCredits(),
                        course.getDepartment(),
                        course.getSemester(),
                        instructorName);
                writer.write(line);
            }
            System.out.println("INFO: Course export finished successfully.");
        } catch (IOException e) {
            System.err.println("FATAL: Failed to write to course export file: " + e.getMessage());
        }
    }
}

