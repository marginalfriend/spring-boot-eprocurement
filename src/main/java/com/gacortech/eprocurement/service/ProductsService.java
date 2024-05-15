package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.entity_rep.Product;
import com.gacortech.eprocurement.dto.request.SearchProductRequest;
import com.gacortech.eprocurement.dto.response.ProductResponse;
import com.gacortech.eprocurement.entity.Products;
import org.springframework.data.domain.Page;

public interface ProductsService {
    ProductResponse create(Product request);
    ProductResponse getById(String id);
    Products entityId(String id);
    Page<Products> getAll(SearchProductRequest request);
    ProductResponse update(Product request);
}
