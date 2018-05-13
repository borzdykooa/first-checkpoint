package by.borzdykooa.entity;

import by.borzdykooa.entity.helpers.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "review", schema = "online_pharmacy")
public class Review extends IdEntity {

    @Column(name = "mark", nullable = false)
    private Integer mark;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User reviewUser;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine reviewMedicine;
}