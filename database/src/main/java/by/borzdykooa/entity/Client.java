package by.borzdykooa.entity;

import by.borzdykooa.entity.helpers.FullName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "client", schema = "online_pharmacy")
@PrimaryKeyJoinColumn(name = "user_id")
public class Client extends User {

    @Column
    @Embedded
    private FullName fullName;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "telephone_number", nullable = false)
    private String telephoneNumber;

    @Column(name = "address", nullable = false)
    private String address;

    public Client(String login, String password, FullName fullName, LocalDate dateOfBirth, String telephoneNumber, String address) {
        super(login, password);
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
    }
}
