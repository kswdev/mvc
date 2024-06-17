package org.example.calculator.grade;

import org.example.calculator.grade.domain.course.Course;
import org.example.calculator.grade.domain.course.Courses;

import java.util.List;

public class GradeCalculator {

    private final Courses courses;
    public GradeCalculator(List<Course> courses) {
        this.courses = new Courses(courses);
    }

    /**
     * 요구사항
     * 평균학점 계산 방법 = (학점수 * 교과목 평점)의 합계/수강신청 총 학점 수
     * 일급 컬렉션 사용
     */

    public double calculateGrade() {
        double multipliedCreditAndGrade = 0;

        // 학점수 * 교과목 평점
        for (Course course : courses.getCourses())
            multipliedCreditAndGrade += course.multipliedCreditAndGrade();

        // 수강 신청 총 학점 수
        int totalCompletedCredit = courses.getCourses().stream()
                .mapToInt(Course::getCredit)
                .sum();

        return multipliedCreditAndGrade / totalCompletedCredit;
    }
}
