package edu.ccrm.domain;

import java.util.Objects;


public final class Course {


    private final String code;
    private final String title;
    private final int credits;
    private final String department;
    private instructor instructor;
    private semester semester;


    private Course(CourseBuilder builder) {
        this.code = CourseBuilder.code;
        this.title = builder.title;
        this.credits = builder.credits;
        this.department = builder.department;
        this.instructor = builder.instructor;
        this.semester = builder.semester;
    }


    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public String getDepartment() {
        return department;
    }

    public instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(instructor instructor) {
        this.instructor = instructor;
    }
    public void setSemester(semester semester)
    {
        this.semester = semester;
    }


    public semester getSemester() {
        return semester;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return code.equals(course.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }




    public static class CourseBuilder {

        private static String code;
        private final String title;
        private int credits;
        private String department;
        private instructor instructor;
        private semester semester;


        public CourseBuilder(String code, String title) {
            CourseBuilder.code = code;
            this.title = title;
        }

        public static String getCode() {
            return code;
        }

        public CourseBuilder withCredits(int credits) {
            this.credits = credits;
            return this;
        }


        public CourseBuilder withDepartment(String department) {
            this.department = department;
            return this;
        }


        public CourseBuilder withInstructor(instructor instructor) {
            this.instructor = instructor;
            return this;
        }


        public CourseBuilder withSemester(semester semester) {
            this.semester = semester;
            return this;
        }


        public Course build() {

            return new Course(this);
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", department='" + department + '\'' +
                ", instructor=" + instructor +
                ", semester=" + semester +
                '}';
    }
}
