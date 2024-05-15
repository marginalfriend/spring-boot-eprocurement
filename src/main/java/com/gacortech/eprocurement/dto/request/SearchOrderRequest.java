package com.gacortech.eprocurement.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchOrderRequest {
    private Integer maxAmount;
    private Integer minAmount;
    private String startFrom;
    private String endTo;
    private String Date;

    private String sortBy;
    private String direction;

    private Integer page;
    private Integer size;
}
