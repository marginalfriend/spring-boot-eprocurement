package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.Product;
import com.gacortech.eprocurement.dto.response.CategoryResponse;
import com.gacortech.eprocurement.dto.response.ProductResponse;
import com.gacortech.eprocurement.entity.Categories;
import com.gacortech.eprocurement.entity.Products;
import com.gacortech.eprocurement.repository.ProductsRepository;
import com.gacortech.eprocurement.service.CategoriesService;
import com.gacortech.eprocurement.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private final ProductsRepository productsRepository;
    private final CategoriesService categoriesService;

    @Override
    public ProductResponse create(Product request) {
        Products newProduct = Products.builder()
                .name(request.getName())
                .categoryId(categoriesService.getById(request.getCategoryId()))
                .build();
        Products savedProduct = productsRepository.saveAndFlush(newProduct);
        return ProductResponse.builder()
                .productName(savedProduct.getName())
                .categoryId(savedProduct.getCategoryId().getId())
                .build();
    }

    @Override
    public ProductResponse getById(String id) {
        Products productFound = findByIdOrThrowNotFound(id);
        return ProductResponse.builder()
                .productName(productFound.getName())
                .categoryId(productFound.getCategoryId().getId())
                .build();
    }

    @Override
    public List<ProductResponse> getAll() {
        List<Products> products = productsRepository.findAll();
        return products.stream()
                .map(prd -> ProductResponse.builder()
                        .productName(prd.getName())
                        .categoryId(prd.getCategoryId().getId())
                        .build()).toList();
    }

    @Override
    public ProductResponse update(Product request) {
        Products product = findByIdOrThrowNotFound(request.getId());
        Products updatedProduct = productsRepository.saveAndFlush(product);
        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .productName(updatedProduct.getName())
                .categoryId(updatedProduct.getCategoryId().getId())
                .build();
    }

    public Products findByIdOrThrowNotFound(String id){
        return productsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND));
    }
}
