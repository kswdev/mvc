package org.example.calculatorExample.grade.domain;

import org.example.calculatorExample.grade.GradeCalculator;
import org.example.calculatorExample.grade.domain.course.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CourseTest {

    @DisplayName("평균 학점을 계산한다.")
    @Test
    void createTest() {

        List<Course> courses = List.of(
                new Course("OOP", 3, "A+"),
                new Course("자료구조", 3, "A+")
        );

        GradeCalculator gradeCalculator = new GradeCalculator(courses);
        double gradeResult = gradeCalculator.calculateGrade();

        assertThat(gradeResult).isEqualTo(4.5);
    }
}