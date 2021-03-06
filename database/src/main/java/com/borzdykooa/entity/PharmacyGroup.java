package com.borzdykooa.entity;

import com.borzdykooa.entity.helpers.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Setter
@Getter
@ToString(exclude = "medicines", callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "pharmacy_group", schema = "online_pharmacy")
public class PharmacyGroup extends IdEntity<Long> {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "pharmacyGroup")
    private Set<Medicine> medicines = new HashSet<>();

    @Version
    @Column(name = "version", nullable = false)
    private Long version;

    public PharmacyGroup(String name) {
        this.name = name;
    }

}
