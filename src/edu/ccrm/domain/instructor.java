package edu.ccrm.domain;


public class instructor extends person
{
    protected String Department;
    protected String specialization;
    public instructor(int id, String name, String email, String Department, String specialization) {
        super(id,name,email);
        this.Department=Department;
        this.specialization=specialization;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getInfo() {
        return "instructor{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", specialization='" + specialization + '\'' +
                ", Department='" + Department + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "instructor{" +
                "specialization='" + specialization + '\'' +
                ", Department='" + Department + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
