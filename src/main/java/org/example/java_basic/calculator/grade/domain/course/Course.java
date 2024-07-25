package org.example.java_basic.calculator.grade.domain.course;

public class Course {


    private final String subject;
    private final int credit;
    private final String grade;

    public String getSubject() {
        return subject;
    }

    public int getCredit() {
        return credit;
    }

    public String getGrade() {
        return grade;
    }

    public Course(String subject, int credit, String grade) {
        this.subject = subject;
        this.credit = credit;
        this.grade = grade;
    }

    private double getGradeToNumber() {
        switch (this.grade) {
            case "A+" : return 4.5;
            case "A"  : return 4.0;
            case "B+" : return 3.5;
            case "B"  : return 3.0;
            case "C+" : return 2.5;
            case "C"  : return 2.0;
            case "D+" : return 1.5;
            case "D"  : return 1.0;
            default : return 0;
        }
    }

    public double multipliedCreditAndGrade() {
        return this.getCredit() * this.getGradeToNumber();
    }
}
