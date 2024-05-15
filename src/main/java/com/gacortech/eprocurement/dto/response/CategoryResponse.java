package com.gacortech.eprocurement.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryResponse {
    private Integer id;
    private String categoryName;
    private List<ProductResponse> products;
}
