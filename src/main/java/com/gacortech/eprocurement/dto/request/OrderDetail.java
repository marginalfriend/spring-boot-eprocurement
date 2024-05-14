package com.gacortech.eprocurement.dto.request;

import com.gacortech.eprocurement.entity.Orders;
import com.gacortech.eprocurement.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDetail {
    private String id;
    private Orders orders;
    private Products product;
    private Integer quantity;
}
