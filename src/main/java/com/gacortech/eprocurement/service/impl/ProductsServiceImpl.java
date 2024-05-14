package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.entity.Products;
import com.gacortech.eprocurement.repository.ProductsRepository;
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

    @Override
    public Products create(Products products) {
        return productsRepository.saveAndFlush(products);
    }

    @Override
    public Products getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<Products> getAll() {
        return productsRepository.findAll();
    }

    @Override
    public Products update(Products products) {
        findByIdOrThrowNotFound(products.getId());
        return productsRepository.saveAndFlush(products);
    }

    public Products findByIdOrThrowNotFound(String id){
        return productsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND));
    }
}
