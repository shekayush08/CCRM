package edu.ccrm.service;


import edu.ccrm.domain.Course;
import edu.ccrm.domain.instructor;
import edu.ccrm.domain.semester;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class CourseService {
    private final List<Course> courses = new ArrayList<>();
    public CourseService() {};

    public void addCourse(Course course) {
        for (Course existingCourse : courses) {
            if (existingCourse.getCode().equals(Course.CourseBuilder.getCode())) {
                throw new IllegalArgumentException("A course with code '" + course.getCode() + "' already exists.");
            }
        }
        courses.add(course);
        System.out.println("SUCCESS: Added course -> " + course.getTitle());
    }
    public Course getCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)){
                return course;
            }
        }
        return null;
    }
    public List<Course> searchCourse(Predicate<Course> filter)
    {
        return courses.stream().filter(filter).collect(Collectors.toList());

    }

    public List<Course> getAllCourse(){
        return new ArrayList<>(courses);
    }


    public boolean assignInstructorToCourse(String courseCode, instructor newInstructor) {
        Course course = getCourseByCode(courseCode);
        if (course != null) {
            course.setInstructor(newInstructor); // This requires a setter on the Course class
            System.out.println("SUCCESS: Assigned " + newInstructor.getName() + " to course " + courseCode);
            return true;
        }
        System.out.println("ERROR: Could not assign instructor. Course with code '" + courseCode + "' not found.");
        return false;
    }

    public boolean updateSemester(String code, semester newsem)
    {
        Course course = getCourseByCode(code);
        if(course != null)
        {
            course.setSemester(newsem);
            System.out.println("course semester updated to" +newsem);
            return true;
        }
        System.out.println("error could not update semester!!!!");
        return false;

    }
}
