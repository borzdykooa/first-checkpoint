package com.borzdykooa.entity;

import com.borzdykooa.entity.helpers.IdEntity;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = {"orderingMedicines", "prescriptions", "reviews"}, callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "medicine", schema = "online_pharmacy")
public class Medicine extends IdEntity<Long> {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "pharmacy_group_id", nullable = false)
    private PharmacyGroup pharmacyGroup;

    @OneToOne
    @JoinColumn(name = "sale_info_id", unique = true)
    private SaleInfo saleInfo;

    @OneToMany(mappedBy = "medicine")
    private Set<OrderingMedicine> orderingMedicines = new HashSet<>();

    @OneToMany(mappedBy = "prescriptionMedicine")
    private Set<Prescription> prescriptions = new HashSet<>();

    @OneToMany(mappedBy = "reviewMedicine")
    private Set<Review> reviews = new HashSet<>();

    public Medicine(String name, String description, PharmacyGroup pharmacyGroup, SaleInfo saleInfo) {
        this.name = name;
        this.description = description;
        this.pharmacyGroup = pharmacyGroup;
        this.saleInfo = saleInfo;
    }
}
