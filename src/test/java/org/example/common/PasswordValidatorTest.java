package org.example.common;

import org.example.common.PasswordValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class PasswordValidatorTest {

    @DisplayName("비밀번호가 최소 8자 이상 12자 이하면 예외가 발생하지 않는다.")
    @Test
    void validatePasswordTest() {
        assertThatCode(() -> PasswordValidator.validate("12345678"))
                .doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만 12자 초과면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"1234567", "1234567891123"})
    void name(String password) {
        assertThatCode(() -> PasswordValidator.validate(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(PasswordValidator.WRONG_PASSWORD_LENGTH_EXCEPTION_MESSAGE);
    }
}
