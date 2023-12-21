package gifttogo.demo.validation;

import gifttogo.demo.exceptions.ValidationException;

public class Validator {
    // Private constructor to prevent instantiation
    private Validator() {}

    /**
     * Validates the username, password, and email of a user.
     *
     * @param username The username to be validated. Must be non-null and follow specific format rules.
     * @param password The password to be validated. Must be non-null and meet complexity requirements.
     * @param email    The email to be validated. Must be non-null and in a valid email format.
     * @throws ValidationException If any of the validation checks fail. The exception message provides details about the specific validation failure.
     */
    public static void validateUser(String username, String password, String email) throws ValidationException {
        validatePassword(password);
        validateUsername(username);
        validateEmail(email);
    }

    /**
     * Validates the provided email address against a standard email format.
     *
     * @param email The email address to be validated.
     * @throws ValidationException If the email does not meet the format requirements.
     */
    private static void validateEmail(String email) throws ValidationException {
        if (!isEmailValid(email)) {
            throw new ValidationException("InvalidEmailError", "Email does not meet the format requirements.");
        }
    }

    /**
     * Checks if the provided email address meets standard email format criteria.
     *
     * @param email The email address to be checked.
     * @return boolean indicating if the email is in a valid format.
     */
    private static boolean isEmailValid(String email) {
        // Simple regex for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }
    /**
     * Responsible for exception handling based on the result of the isPasswordComplex check.
     * Uses isPasswordComplex to determine if the password is valid.
     * If the password is not complex enough, it throws a ValidationException.
     *
     * @param password The password to be validated.
     * @throws ValidationException If the password does not meet the complexity requirements.
     */
    private static void validatePassword(String password) throws ValidationException {
        if (!isPasswordComplex(password)) {
            throw new ValidationException("InvalidPasswordError", "Password does not meet the complexity requirements.");
        }
    }

    /**
     * Responsible for exception handling based on the result of the isUsernameValid check.
     * Uses isUsernameValid to determine if the username is valid.
     * If the username does not meet the requirements, it throws a ValidationException.
     *
     * @param username The username to be validated.
     * @throws ValidationException If the username does not meet the requirements.
     */
    private static void validateUsername(String username) throws ValidationException {
        if (!isUsernameValid(username)) {
            throw new ValidationException("InvalidUsernameError", "Username does not meet the requirements.");
        }
    }

    /**
     * Contains the specific logic to check if a password meets the complexity criteria
     * (e.g., length, containing numbers and letters, special characters, etc.).
     * It returns a boolean value indicating whether the password is complex enough.
     *
     * @param password The password to be checked.
     * @return boolean indicating if the password meets the complexity requirements.
     */
    private static boolean isPasswordComplex(String password) {
        // Example criteria: Password must be at least 8 characters long, contain at least one number and one letter
        return password != null && password.matches("(?=.*[0-9])(?=.*[a-zA-Z]).{8,}");
    }

    /**
     * Contains the specific logic to check if a username meets the validity criteria
     * (e.g., non-empty, alphanumeric, etc.).
     * It returns a boolean value indicating whether the username is valid.
     *
     * @param username The username to be checked.
     * @return boolean indicating if the username is valid.
     */
    private static boolean isUsernameValid(String username) {
        // Example criteria: Username must be alphanumeric and non-empty
        return username != null && !username.isEmpty() && username.matches("[A-Za-z0-9]+");
    }
}
