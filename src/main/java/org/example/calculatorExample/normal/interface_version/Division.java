package org.example.calculatorExample.normal.interface_version;

import org.example.calculatorExample.normal.interface_version.number.PositiveNumber;

public class Division implements ArithmeticOperator{
    @Override
    public boolean support(String operator) {
        return "/".equals(operator);
    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.number() / operand2.number();
    }
}
