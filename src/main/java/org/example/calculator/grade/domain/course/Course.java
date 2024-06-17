package org.example.calculator.grade.domain.course;

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
        return switch (this.grade) {
            case "A+" -> 4.5;
            case "A"  -> 4.0;
            case "B+" -> 3.5;
            case "B"  -> 3.0;
            case "C+" -> 2.5;
            case "C"  -> 2.0;
            case "D+" -> 1.5;
            case "D"  -> 1.0;
            default -> 0;
        };
    }

    public double multipliedCreditAndGrade() {
        return this.getCredit() * this.getGradeToNumber();
    }
}
