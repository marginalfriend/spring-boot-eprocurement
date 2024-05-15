package com.gacortech.eprocurement.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductSupplyResponse {
    Integer id;
    String  productName;
    String  vendorName;
    Integer stock;
}