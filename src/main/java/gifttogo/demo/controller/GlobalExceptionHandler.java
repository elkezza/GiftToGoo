package gifttogo.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This class provides global exception handling across the application,
 * ensuring that all exceptions are caught and handled gracefully.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles any uncaught exceptions that bubble up during the execution of the application.
     *
     * @param throwable The exception that was thrown.
     * @param model     The model to which the error attributes are added.
     * @return The path to the 'error.html' template.
     */
    @ExceptionHandler(Throwable.class)
    public String handleException(Throwable throwable, Model model) {
        model.addAttribute("error", throwable.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return "error/error"; // Assuming the error.html is inside an 'error' folder.
    }
}
