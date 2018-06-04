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
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@Setter
@Getter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "prescription", schema = "online_pharmacy")
public class Prescription extends IdEntity<Long> {

    @Column(name = "name", unique = true, nullable = false)
    private Long name;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User prescriptionUser;

    @ManyToOne
    @JoinColumn(name = "medicine_id", nullable = false)
    private Medicine prescriptionMedicine;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "validity", nullable = false)
    private LocalDate validity;
}
