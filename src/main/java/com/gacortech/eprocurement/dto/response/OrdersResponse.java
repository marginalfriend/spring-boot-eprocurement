package com.gacortech.eprocurement.dto.response;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdersResponse {
    private String id;
    private String orderDate;
    private Integer totalAmount;
    private List<OrderDetailResponse> orderDetails;




}
