package gifttogo.demo.model.user;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId",
            updatable= false,
            nullable = false)
    private Long userId;

    private String username;
    private String email;
    private String password; // Store hashed passwords only

    // Enum for user roles

    @Enumerated(EnumType.STRING)
    private Role role;


    public enum Role {
        USER, ADMIN
    }

    // Constructor
    public User() {
        // Default constructor
    }

    public User(Long userId, String username, String email, String password, Role role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Method to check if the user is an admin
    public boolean isAdmin() {
        return this.role == Role.ADMIN;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) && username.equals(user.username) && email.equals(user.email) && password.equals(user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, email, password, role);
    }
}
