package gifttogo.demo.controller;

import gifttogo.demo.exceptions.NotFoundException;
import gifttogo.demo.exceptions.ValidationException;
import gifttogo.demo.model.Offer;
import gifttogo.demo.model.user.User;
import gifttogo.demo.service.UserService;
import gifttogo.demo.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class that handles web requests to the application.
 */
@Controller
@RequestMapping("/main")
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login() {
        return "login/login-page"; // name of your HTML file without the extension
    }


    /**
     * Displays the data entry form to the user.
     *
     * @return The name of the 'data-entry.html' view to be rendered.
     */
    @GetMapping("/data-entry")
    public String showDataEntry(Model model) {
        Offer offer = new Offer(); // Create a new offer object
        model.addAttribute("offer", offer); // Add it to the model
        return "data-entry/data-entry"; // This should match the folder structure where the HTML file is located.
    }

    /**
     * Processes the login request by authenticating the provided username and password.
     * Redirects to the data entry page if authentication is successful,
     * otherwise returns to the login page with an error message.
     *
     * @param usernameOrEmail The username OR email provided by the user.
     * @param password The password provided by the user.
     * @param model The Spring Model to pass attributes to the view.
     * @return The name of the view to be rendered.
     */
    @PostMapping("/process-login")
    public String processLogin(@RequestParam String usernameOrEmail, @RequestParam String password, Model model) {
        try {
            logger.info("Username: " + usernameOrEmail);
            logger.info("Password: " + password);
            boolean isAuthenticated = authenticateUser(usernameOrEmail, password);

            if (isAuthenticated) {
                logger.debug("Access Granted!");
                return "redirect:/main/data-entry";
            } else {
                logger.debug("Access Denied!");
                model.addAttribute("error", "Invalid username or password");
                return "login/login-page";
            }
        } catch (NotFoundException e) {
            model.addAttribute("error", e.getMessage());
            return "login/login-page";
        }
    }


    /**
     * Authenticates a user based on username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @return True if authentication is successful, False otherwise.
     */
    private boolean authenticateUserOld(String username, String password) {
        // TODO: Implement actual authentication logic, e.g., checking against database
        return false;
    }

    private boolean authenticateUser(String usernameOrEmail, String password) {
        try {
            // Validate the input
//            Validator.validateUsername(username);
//            Validator.validatePassword(password);

            // If validation passes, proceed to check against database
            // Assuming userService is a service class that interacts with your user repository
            User user = userService.findByUsernameOrEmail(usernameOrEmail);
            userService.authenticateUser(usernameOrEmail, password);
            if (userService.findByUsernameOrEmail(usernameOrEmail) != null && userService.authenticateUser(usernameOrEmail, password)) {
                return true; // User found and password matches
            }
        } catch (ValidationException e) {
            // Handle validation exceptions (e.g., logging, etc.)
        }
        return false; // Default return if validation fails or user not found / password mismatch
    }
}
