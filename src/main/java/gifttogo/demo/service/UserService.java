package gifttogo.demo.service;

import gifttogo.demo.exceptions.*;
import gifttogo.demo.model.Brand;
import gifttogo.demo.model.user.User;
import gifttogo.demo.repository.BrandRepository;
import gifttogo.demo.repository.UserRepository;
import gifttogo.demo.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;



    @Autowired
    private BrandRepository brandRepository;

    // Method for an admin to create a new user
    public User createNewUser(User admin, User newUser) {
        if (admin.isAdmin()) {
            // Only allow if admin is creating the user
            Validator.validateUser(newUser.getUsername(), newUser.getPassword(), newUser.getEmail());

            return userRepository.save(newUser);
        } else {
            throw new UnauthorizedException("UnauthorizedException", "Only admins can create new users.");
        }
    }

    public Brand createBrandForUser(Brand brand, Long userId) {
        // Find the user, set the user to the brand, and save it
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return brandRepository.save(brand);
    }

    public User findUserById(Long id){

        return userRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("NotFoundException", "User with id " + id + " not found!"));

    }

    /**
     * Retrieves a User entity based on the provided username or email.
     * Throws a custom exception if the user is not found.
     *
     * @param usernameOrEmail The username or email of the user to be retrieved.
     * @return User entity if found.
     * @throws NotFoundException if no user is found with the given username or email.
     */
    public User findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new NotFoundException("UserNotFoundException","User not found with username or email: " + usernameOrEmail));

    }

    /**
     * Authenticates a user based on username/email and password.
     *
     * @param usernameOrEmail The username or email of the user.
     * @param password        The password of the user.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticateUser(String usernameOrEmail, String password) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail)
                .orElseThrow(() -> new NotFoundException("UserNotFoundException","User not found with username or email: " + usernameOrEmail));

        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    // ... other service methods
}
