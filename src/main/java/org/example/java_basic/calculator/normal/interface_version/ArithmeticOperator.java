package org.example.java_basic.calculator.normal.interface_version;

import org.example.java_basic.calculator.normal.interface_version.number.PositiveNumber;

public interface ArithmeticOperator {

    boolean support(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
