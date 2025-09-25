package edu.ccrm.service;

import edu.ccrm.domain.student;

import java.util.ArrayList;
import java.util.List;


public class StudentService {


    private final List<student> students = new ArrayList<>();


    private int nextId = 1;


    public student addStudent(String fullName, String email, String regNo) {

        for (student existingStudent : students) {
            if (existingStudent.getRegno().equals(regNo)) {
                throw new IllegalArgumentException("A student with registration number '" + regNo + "' already exists.");
            }
        }

        student newStudent = new student(nextId++, fullName, email, regNo);
        students.add(newStudent);
        System.out.println("SUCCESS: Created student -> " + newStudent);
        return newStudent;
    }


    public student getStudentById(int id) {
        for (student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }


    public student getStudentByRegNo(String regNo) {
        for (student student : students) {
            if (student.getRegno().equalsIgnoreCase(regNo)) {
                return student;
            }
        }
        return null; // Return null if no student is found with that registration number
    }


    public boolean updateStudent(int id, String newFullName, String newEmail, String newStatus) {
        student student = getStudentById(id);
        if (student != null) {
            student.setName(newFullName);
            student.setEmail(newEmail);
            student.setStatus(newStatus);
            System.out.println("SUCCESS: Updated details for student ID " + id);
            return true;
        }
        System.out.println("ERROR: Could not update. Student with ID '" + id + "' not found.");
        return false;
    }


    public List<student> getAllStudents() {

        return new ArrayList<>(students);
    }
}
