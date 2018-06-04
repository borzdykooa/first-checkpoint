package com.borzdykooa.entity;

import com.borzdykooa.entity.enums.UserRole;
import com.borzdykooa.entity.helpers.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"orders", "prescriptions", "reviews"}, callSuper = true)
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user", schema = "online_pharmacy")
public abstract class User extends IdEntity<Long> {

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private Set<Ordering> orders = new HashSet<>();

    @OneToMany(mappedBy = "prescriptionUser")
    private Set<Prescription> prescriptions = new HashSet<>();

    @OneToMany(mappedBy = "reviewUser")
    private Set<Review> reviews = new HashSet<>();

    public User(String login, String password, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.userRole = userRole;
    }
}
