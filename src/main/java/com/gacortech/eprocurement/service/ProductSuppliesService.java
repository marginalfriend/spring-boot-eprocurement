package com.gacortech.eprocurement.service;


import com.gacortech.eprocurement.entity.ProductSupplies;

import java.util.List;

public interface ProductSuppliesService {
    List<ProductSupplies> getAll();
    ProductSupplies getByid(Integer i);
    ProductSupplies create(ProductSupplies productSupply);
    ProductSupplies update(ProductSupplies productSupply);
}
