package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.entity.Products;

import java.util.List;

public interface ProductsService {
    Products create(Products products);
    Products getById(String id);
    List<Products> getAll();
    Products update(Products products);
}
