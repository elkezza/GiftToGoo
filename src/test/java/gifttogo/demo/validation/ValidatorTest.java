package gifttogo.demo.validation;

import gifttogo.demo.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validateUser() {
    }

    /**
     * Tests validateUser method with all valid inputs.
     * Expects no exceptions to be thrown.
     */
    @Test
    void validateUser_AllValidInputs() {
        assertDoesNotThrow(() -> Validator.validateUser("validUser", "ValidPass123", "valid@example.com"));
    }

    /**
     * Tests validateUser method with an invalid username.
     * Expects ValidationException to be thrown.
     */
    @Test
    void validateUser_InvalidUsername() {
        assertThrows(ValidationException.class, () -> Validator.validateUser("invalid user!", "ValidPass123", "valid@example.com"));
    }

    /**
     * Tests validateUser method with an invalid password.
     * Expects ValidationException to be thrown.
     */
    @Test
    void validateUser_InvalidPassword() {
        assertThrows(ValidationException.class, () -> Validator.validateUser("validUser", "short", "valid@example.com"));
    }

    /**
     * Tests validateUser method with an invalid email.
     * Expects ValidationException to be thrown.
     */
    @Test
    void validateUser_InvalidEmail() {
        assertThrows(ValidationException.class, () -> Validator.validateUser("validUser", "ValidPass123", "invalid-email"));
    }

    /**
     * Tests validateUser method with a null username.
     * Expects ValidationException or a specific NullPointerException to be thrown.
     */
    @Test
    void validateUser_NullUsername() {
        assertThrows(Exception.class, () -> Validator.validateUser(null, "ValidPass123", "valid@example.com"));
    }

    /**
     * Tests validateUser method with a null password.
     * Expects ValidationException or a specific NullPointerException to be thrown.
     */
    @Test
    void validateUser_NullPassword() {
        assertThrows(Exception.class, () -> Validator.validateUser("validUser", null, "valid@example.com"));
    }

    /**
     * Tests validateUser method with a null email.
     * Expects ValidationException or a specific NullPointerException to be thrown.
     */
    @Test
    void validateUser_NullEmail() {
        assertThrows(Exception.class, () -> Validator.validateUser("validUser", "ValidPass123", null));
    }

    /**
     * Parameterized test for validateUser method with various input combinations.
     * @param username Username to be validated.
     * @param password Password to be validated.
     * @param email Email to be validated.
     * @param shouldThrow Whether a ValidationException is expected.
     */
    @ParameterizedTest
    @CsvSource({
            "validUser, ValidPass123, valid@example.com, false",
            "invalid user!, ValidPass123, valid@example.com, true",
            "validUser, short, valid@example.com, true",
            //  more combinations can be added as needed
    })
    void validateUser_Parameterized(String username, String password, String email, boolean shouldThrow) {
        if (shouldThrow) {
            assertThrows(ValidationException.class, () -> Validator.validateUser(username, password, email));
        } else {
            assertDoesNotThrow(() -> Validator.validateUser(username, password, email));
        }
    }
}