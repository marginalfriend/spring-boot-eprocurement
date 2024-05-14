package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.Product;
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
    public Products create(Product product) {
        Products newProduct = Products.builder()
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .build();
        return productsRepository.saveAndFlush(newProduct);
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
    public Products update(Product product) {
        findByIdOrThrowNotFound(product.getId());
        Products updateProduct = Products.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategoryId())
                .build();
        return productsRepository.saveAndFlush(updateProduct);
    }

    public Products findByIdOrThrowNotFound(String id){
        return productsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND));
    }
}
