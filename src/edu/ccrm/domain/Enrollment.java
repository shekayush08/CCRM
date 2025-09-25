package edu.ccrm.domain;

import java.time.LocalDate;


public class Enrollment
{
    private student student;
    private Course course;
    private grades grade;
    private LocalDate enrollmentDate;

    public Enrollment(student student, Course course)
    {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
    }

    public student getStudent() {
        return student;
    }

    public void setStudent(student student) {
        this.student = student;
    }

    public grades getGrade() {
        return grade;
    }

    public void setGrade(grades grade) {
        this.grade = grade;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }


    public String getEnrollments() {
        return "Enrollment{" +
                "student=" + student +
                ", course=" + course +
                ", grade=" + grade +
                ", enrollmentDate=" + enrollmentDate +
                '}';
    }
}
