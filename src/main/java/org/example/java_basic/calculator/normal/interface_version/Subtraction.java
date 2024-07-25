package org.example.java_basic.calculator.normal.interface_version;

import org.example.java_basic.calculator.normal.interface_version.number.PositiveNumber;

public class Subtraction implements ArithmeticOperator{
    @Override
    public boolean support(String operator) {
        return "-".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.getNumber() - operand2.getNumber();
    }
}
