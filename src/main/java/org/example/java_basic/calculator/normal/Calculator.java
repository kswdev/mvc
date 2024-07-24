package org.example.java_basic.calculator.normal;

import org.example.java_basic.calculator.normal.interface_version.number.PositiveNumber;
import org.example.java_basic.calculator.normal.interface_version.*;

import java.util.List;

public class Calculator {

    private static final List<ArithmeticOperator> operators = List.of(
            new Addition(),
            new Subtraction(),
            new Multiplication(),
            new Division()
    );

    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        /*return ArithmeticOperator.calculate(operand1, operator, operand2);*/
        return operators.stream()
                .filter(op -> op.support(operator))
                .map(op -> op.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported operation"));
    }
}
