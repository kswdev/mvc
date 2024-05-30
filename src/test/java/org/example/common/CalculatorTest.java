package org.example.common;

import org.example.common.calculator.Calculator;
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
        int calculatedResult = Calculator.calculate(operand1, operator, operand2);

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