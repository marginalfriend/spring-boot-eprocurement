package com.gacortech.eprocurement.service;


import com.gacortech.eprocurement.entity.ProductSupply;

import java.util.List;

public interface ProductSupplyService {
    List<ProductSupply> getAll();
    ProductSupply getByid(Integer i);
    ProductSupply create(ProductSupply productSupply);
    ProductSupply update(ProductSupply productSupply);
}
