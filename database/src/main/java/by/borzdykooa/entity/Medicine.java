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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"orderingMedicines", "prescriptions", "reviews"})
@NoArgsConstructor
@Entity
@Table(name = "medicine", schema = "online_pharmacy")
public class Medicine extends IdEntity {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "need_prescription", nullable = false)
    private Boolean needPrescription;

    @ManyToOne
    @JoinColumn(name = "pharmacy_group_id", nullable = false)
    private PharmacyGroup pharmacyGroup;

    @OneToMany(mappedBy = "medicine")
    private Set<OrderingMedicine> orderingMedicines = new HashSet<>();

    @OneToMany(mappedBy = "prescriptionMedicine")
    private Set<Prescription> prescriptions = new HashSet<>();

    @OneToMany(mappedBy = "reviewMedicine")
    private Set<Review> reviews = new HashSet<>();

    public Medicine(String name, String description, BigDecimal price, Long quantity, Boolean needPrescription, PharmacyGroup pharmacyGroup) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.needPrescription = needPrescription;
        this.pharmacyGroup = pharmacyGroup;
    }
}
