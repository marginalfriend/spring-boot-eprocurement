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
    private String date;
    private String month;

    private String sortBy;
    private String direction;

    private Integer page;
    private Integer size;
}
