package com.gacortech.eprocurement.dto.entity_rep;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductSupply {
    private Integer id;
    private String productId;
    private String vendorId;
    private Integer stock;
}
