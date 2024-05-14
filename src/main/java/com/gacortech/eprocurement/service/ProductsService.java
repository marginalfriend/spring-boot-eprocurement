package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.entity_rep.Product;
import com.gacortech.eprocurement.entity.Products;

import java.util.List;

public interface ProductsService {
    Products create(Product product);
    Products getById(String id);
    List<Products> getAll();
    Products update(Product product);
}
