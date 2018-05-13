package by.borzdykooa.entity;

import by.borzdykooa.entity.helpers.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"orders", "prescriptions", "reviews"})
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user", schema = "online_pharmacy")
public abstract class User extends IdEntity {

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Ordering> orders = new HashSet<>();

    @OneToMany(mappedBy = "prescriptionUser")
    private Set<Prescription> prescriptions = new HashSet<>();

    @OneToMany(mappedBy = "reviewUser")
    private Set<Review> reviews = new HashSet<>();

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
