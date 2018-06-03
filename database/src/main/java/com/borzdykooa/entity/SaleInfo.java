package com.borzdykooa.entity;

import com.borzdykooa.entity.helpers.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "saleInfoMedicine", callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "sale_info", schema = "online_pharmacy")
public class SaleInfo extends IdEntity<Long> {

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "need_prescription", nullable = false)
    private Boolean needPrescription;

    @OneToOne(mappedBy = "saleInfo")
    private Medicine saleInfoMedicine;

    public SaleInfo(BigDecimal price, Long quantity, Boolean needPrescription) {
        this.price = price;
        this.quantity = quantity;
        this.needPrescription = needPrescription;
    }
}
