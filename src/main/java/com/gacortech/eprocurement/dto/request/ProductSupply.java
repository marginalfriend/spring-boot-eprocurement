package com.gacortech.eprocurement.dto.request;

import com.gacortech.eprocurement.entity.Products;
import com.gacortech.eprocurement.entity.Vendors;

public class ProductSupply {
    private Integer id;
    private Products productId;
    private Vendors vendorId;
    private Integer stock;
    private Integer price;
}
