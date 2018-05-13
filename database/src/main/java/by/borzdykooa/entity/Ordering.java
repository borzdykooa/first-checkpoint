package by.borzdykooa.entity;

import by.borzdykooa.entity.enums.Status;
import by.borzdykooa.entity.helpers.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "orderingMedicines")
@NoArgsConstructor
@Entity
@Table(name = "ordering", schema = "online_pharmacy")
public class Ordering extends IdEntity {

    @Column(name = "date_of_ordering", nullable = false)
    private LocalDate dateOfOrdering;

    @Column(name = "ordering_clothing_date")
    private LocalDate orderingClothingDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "total_sum", nullable = false)
    private BigDecimal totalSum;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "ordering")
    private Set<OrderingMedicine> orderingMedicines = new HashSet<>();

    public Ordering(LocalDate dateOfOrder, LocalDate orderClothingDate, Status status, BigDecimal totalSum, User user) {
        this.dateOfOrdering = dateOfOrder;
        this.orderingClothingDate = orderClothingDate;
        this.status = status;
        this.totalSum = totalSum;
        this.user = user;
    }
}
