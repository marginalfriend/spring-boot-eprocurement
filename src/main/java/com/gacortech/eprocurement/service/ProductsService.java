package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.entity_rep.Product;
import com.gacortech.eprocurement.dto.response.ProductResponse;
import com.gacortech.eprocurement.entity.Products;

import java.util.List;

public interface ProductsService {
    ProductResponse create(Product request);
    ProductResponse getById(String id);
    Products entityId(String id);
    List<ProductResponse> getAll(Product request);
    ProductResponse update(Product request);
}
