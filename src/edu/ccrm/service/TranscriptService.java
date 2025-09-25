package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.student;
import edu.ccrm.domain.student;

import java.text.DecimalFormat;


public class TranscriptService {


    private static final DecimalFormat GPA_FORMATTER = new DecimalFormat("0.00");


    public TranscriptService() {

    }

    public String generateTranscript(student student) {
        StringBuilder transcriptBuilder = new StringBuilder();


        transcriptBuilder.append("==============================================================\n");
        transcriptBuilder.append("           ACADEMIC TRANSCRIPT\n");
        transcriptBuilder.append("==============================================================\n\n");


        transcriptBuilder.append("Student ID:   ").append(student.getId()).append("\n");
        transcriptBuilder.append("Student Name: ").append(student.getName()).append("\n");
        transcriptBuilder.append("Reg No:       ").append(student.getRegno()).append("\n");
        transcriptBuilder.append("Email:        ").append(student.getEmail()).append("\n\n");


        transcriptBuilder.append("---------------------------------------------------------------\n");
        transcriptBuilder.append("Enrolled Courses:\n");
        transcriptBuilder.append("---------------------------------------------------------------\n");
        transcriptBuilder.append(String.format("%-10s | %-25s | %-7s | %-5s\n", "Code", "Title", "Credits", "Grade"));
        transcriptBuilder.append("---------------------------------------------------------------\n");

        if (student.getEnrollments().isEmpty()) {
            transcriptBuilder.append("No courses enrolled.\n");
        } else {
            for (Enrollment enrollment : student.getEnrollments()) {
                String gradeStr = (enrollment.getGrade() != null) ? enrollment.getGrade().name() : "In Progress";
                transcriptBuilder.append(String.format("%-10s | %-25s | %-7d | %-5s\n",
                        enrollment.getCourse().getCode(),
                        enrollment.getCourse().getTitle(),
                        enrollment.getCourse().getCredits(),
                        gradeStr));
            }
        }
        transcriptBuilder.append("---------------------------------------------------------------\n\n");


        double gpa = calculateGPA(student);
        transcriptBuilder.append("Cumulative GPA: ").append(GPA_FORMATTER.format(gpa)).append("\n");
        transcriptBuilder.append("===============================================================\n");

        return transcriptBuilder.toString();
    }


    public double calculateGPA(student student) {
        double totalGradePoints = 0;
        int totalCredits = 0;

        for (Enrollment enrollment : student.getEnrollments()) {

            if (enrollment.getGrade() != null) {
                int credits = enrollment.getCourse().getCredits();
                double gradePoint = enrollment.getGrade().getGradePoint();

                totalGradePoints += (gradePoint * credits);
                totalCredits += credits;
            }
        }


        if (totalCredits == 0) {
            return 0.0;
        }

        return totalGradePoints / totalCredits;
    }
}

