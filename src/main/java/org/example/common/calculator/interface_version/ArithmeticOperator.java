package org.example.common.calculator.interface_version;

import org.example.common.calculator.interface_version.number.PositiveNumber;

public interface ArithmeticOperator {

    boolean support(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
