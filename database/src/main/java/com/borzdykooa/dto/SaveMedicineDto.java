package com.borzdykooa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveMedicineDto {

    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private Boolean needPrescription;
    private Long pharmacyGroup;
    private Long saleInfo;
}
