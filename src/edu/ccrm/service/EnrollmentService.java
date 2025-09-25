package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.student;

import edu.ccrm.exception.DuplicateEnrollmentException;
import edu.ccrm.exception.MaxCreditLimitExceededException;


public class EnrollmentService {


    private static final int MAX_CREDITS_PER_SEMESTER = 21;

    private final StudentService studentService;
    private final CourseService courseService;


    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }


    public void enrollStudentInCourse(int studentId, String courseCode)
            throws DuplicateEnrollmentException, MaxCreditLimitExceededException {


        student student = studentService.getStudentById(studentId);
        if (student == null) {
            System.out.println("ERROR: Enrollment failed. Student with ID " + studentId + " not found.");

            return;
        }

        Course course = courseService.getCourseByCode(courseCode);
        if (course == null) {
            System.out.println("ERROR: Enrollment failed. Course with code '" + courseCode + "' not found.");

            return;
        }


        for (Enrollment existingEnrollment : student.getEnrollments()) {
            if (existingEnrollment.getCourse().getCode().equals(courseCode)) {
                throw new DuplicateEnrollmentException("Student '" + student.getName() + "' is already enrolled in course '" + courseCode + "'.");
            }
        }


        int currentCredits = student.getTotalCredits();
        int newCourseCredits = course.getCredits();

        if ((currentCredits + newCourseCredits) > MAX_CREDITS_PER_SEMESTER) {
            throw new MaxCreditLimitExceededException("Cannot enroll. This would exceed the max credit limit of " +
                    MAX_CREDITS_PER_SEMESTER + ". Student currently has " + currentCredits + " credits.");
        }


        Enrollment newEnrollment = new Enrollment(student, course);
        student.addEnrollment(newEnrollment);

        System.out.println("SUCCESS: Enrolled student '" + student.getName() + "' in course '" + course.getTitle() + "'.");
    }
}
