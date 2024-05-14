package com.gacortech.eprocurement.dto.entity_rep;

import com.gacortech.eprocurement.entity.Products;
import com.gacortech.eprocurement.entity.Vendors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductSupply {
    private Integer id;
    private Products productId;
    private Vendors vendorId;
    private Integer stock;
    private Integer price;
}
