package org.example.java_basic.calculator.normal;

import org.example.java_basic.calculator.normal.Calculator;
import org.example.java_basic.calculator.normal.interface_version.number.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculatorTest {



    @DisplayName("사칙연산을 정상적으로 실행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void additionTest(int operand1, String operator, int operand2, int result) {
        int calculatedResult = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        assertThat(result).isEqualTo(calculatedResult);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1, "+", 1, 2),
                arguments(1, "-", 2, -1),
                arguments(1, "*", 4, 4),
                arguments(6, "/", 3, 2)
        );
    }
}