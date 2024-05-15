package com.gacortech.eprocurement.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDetailResponse {
    private String  id;
    private Integer supplyId;
    private String  productName;
    private Integer quantity;
    private Integer price;
    private Integer totalAmount;
}
