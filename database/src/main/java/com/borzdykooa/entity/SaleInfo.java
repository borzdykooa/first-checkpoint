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
import javax.persistence.Version;
import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "medicine")
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

    @Version
    @Column(name = "version")
    private Long version;

    @OneToOne(mappedBy = "saleInfo")
    private Medicine medicine;

    public SaleInfo(BigDecimal price, Long quantity, Boolean needPrescription) {
        this.price = price;
        this.quantity = quantity;
        this.needPrescription = needPrescription;
    }
}
