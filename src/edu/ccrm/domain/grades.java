package edu.ccrm.domain;

public enum grades
{
    S(10),
    A(9),
    B(8),
    C(7),
    D(6),
    E(5),
    F(0);
    private final int gradePoint;
    private grades(int gradePoint)
        {
        this.gradePoint = gradePoint;
        }
        public int getGradePoint()
        {
        return gradePoint;
        }

}
