package org.example.java_basic.calculator.normal.interface_version.number;

public class PositiveNumber {

    private final int number;

    public PositiveNumber(int number) {
        validate(number);
        this.number = number;
    }

    private void validate(int num) {
        if (num <= 0)
            throw new IllegalArgumentException("음수는 지원하지 않습니다.");
    }

    public int getNumber() {
        return number;
    }
}
