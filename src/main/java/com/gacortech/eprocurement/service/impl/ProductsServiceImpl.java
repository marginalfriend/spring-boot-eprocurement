package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.Product;
import com.gacortech.eprocurement.dto.request.SearchProductRequest;
import com.gacortech.eprocurement.dto.response.ProductResponse;
import com.gacortech.eprocurement.entity.Products;
import com.gacortech.eprocurement.repository.ProductsRepository;
import com.gacortech.eprocurement.service.CategoriesService;
import com.gacortech.eprocurement.service.ProductsService;
import com.gacortech.eprocurement.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
                .category(categoriesService.entityById(request.getCategoryId()))
                .build();
        productsRepository.saveAndFlush(newProduct);
        return ProductResponse.builder()
                .id(newProduct.getId())
                .productName(newProduct.getName())
                .categoryId(newProduct.getCategory().getId())
                .build();
    }

    @Override
    public ProductResponse getById(String id) {
        Products productFound = productsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND));
        return ProductResponse.builder()
                .id(productFound.getId())
                .productName(productFound.getName())
                .categoryId(productFound.getCategory().getId())
                .build();
    }

    @Override
    public Products entityId(String id) {
        return productsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND));
    }

    @Override
    public Page<Products> getAll(SearchProductRequest request) {
        if(request.getPage() <= 0){
            request.setPage(1);
        }

        String validSortBy;
        if("name".equalsIgnoreCase(request.getSortBy())){
            validSortBy = request.getSortBy();
        } else {
            validSortBy = "name";
        }
        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), validSortBy);
        Pageable pageable = PageRequest.of((request.getPage()-1), request.getSize(), sort);
        Specification<Products> specification = ProductSpecification.getSpecification(request);
        return productsRepository.findAll(specification, pageable);
    }

    //    @Override
//    public Page<ProductResponse> getAll(SearchProductRequest request) {
//        if(request.getPage() <= 0){
//            request.setPage(1);
//        }
//
//        String validSortBy;
//        if("name".equalsIgnoreCase(request.getSortBy())){
//            validSortBy = request.getSortBy();
//        } else {
//            validSortBy = "name";
//        }
//
//        Sort sort = Sort.by(Sort.Direction.fromString(request.getDirection()), validSortBy);
//        Pageable pageable = PageRequest.of((request.getPage()-1), request.getSize(), sort);
//        Specification<Products> specification = ProductSpecification.getSpecification(request);
//        productsRepository.findAll(specification, pageable);
////        return products.stream()
////                .map(prd -> ProductResponse.builder()
////                        .id(prd.getId())
////                        .productName(prd.getName())
////                        .categoryId(prd.getCategory().getId())
////                        .build()).toList();
//    }

    @Override
    public ProductResponse update(Product request) {
        Products updateProduct = entityId(request.getId());
        productsRepository.saveAndFlush(updateProduct);
        return ProductResponse.builder()
                .id(updateProduct.getId())
                .productName(updateProduct.getName())
                .categoryId(updateProduct.getCategory().getId())
                .build();
    }

}
