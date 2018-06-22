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

    private Integer limit;
    private Integer page;
//    private Integer offset;
    private String partName;
    private String partDescription;
    private Boolean needPrescription;
}
