package com.gacortech.eprocurement.service;


import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.entity.ProductSupplies;

import java.util.List;

public interface ProductSuppliesService {
    List<ProductSupplies> getAll();
    ProductSupplies getByid(Integer i);
    ProductSupplies create(ProductSupply productSupply);
    ProductSupplies update(ProductSupply productSupply);
}
