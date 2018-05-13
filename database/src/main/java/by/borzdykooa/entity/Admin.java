package by.borzdykooa.entity;

import by.borzdykooa.entity.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "admin", schema = "online_pharmacy")
@PrimaryKeyJoinColumn(name = "user_id")
public class Admin extends User {

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Admin(String login, String password, Role role) {
        super(login, password);
        this.role = role;
    }
}
