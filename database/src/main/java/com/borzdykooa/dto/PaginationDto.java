package com.borzdykooa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationDto {

    Integer limit;
    Integer page;
    String partName;
    String partDescription;
    Boolean needPrescription;
}
