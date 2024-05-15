package com.gacortech.eprocurement.service;


import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.dto.response.ProductSupplyResponse;
import com.gacortech.eprocurement.entity.ProductSupplies;

import java.util.List;

public interface ProductSuppliesService {
    List<ProductSupplyResponse> getAll(ProductSupply productSupply);
    ProductSupplies getByid(Integer i);
    ProductSupplyResponse create(ProductSupply productSupply);
    ProductSupplyResponse update(ProductSupply productSupply);
    ProductSupplyResponse responseById(Integer i);
}
