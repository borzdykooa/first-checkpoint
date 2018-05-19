package com.borzdykooa.entity;

import com.borzdykooa.entity.enums.AdminRole;
import com.borzdykooa.entity.enums.UserRole;
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

    @Column(name = "admin_role", nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminRole adminRole;

    public Admin(String login, String password, UserRole userRole, AdminRole adminRole) {
        super(login, password, userRole);
        this.adminRole = adminRole;
    }
}
