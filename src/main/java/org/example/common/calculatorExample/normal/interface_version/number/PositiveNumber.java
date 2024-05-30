package org.example.common.calculatorExample.normal.interface_version.number;

public record PositiveNumber(int number) {

    public PositiveNumber {
        validate(number);
    }

    private void validate(int num) {
        if (num <= 0)
            throw new IllegalArgumentException("음수는 지원하지 않습니다.");
    }
}
