package gifttogo.demo.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
@Entity
@Table(name = "admins")
@PrimaryKeyJoinColumn(foreignKey = @ForeignKey(name = "userId_fk"))
public class Admin extends User {
    @Column(name = "experience")
    private int experience;
    public Admin() {
        super();
        this.setRole(User.Role.ADMIN);
    }

    public Admin(int experience ) {
        super();
        this.experience = experience;
        this.setRole(User.Role.ADMIN);
    }

    // Getter and setter for experience
    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
