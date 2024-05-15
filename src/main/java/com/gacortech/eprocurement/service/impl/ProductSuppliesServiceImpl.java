package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.dto.response.ProductSupplyResponse;
import com.gacortech.eprocurement.entity.ProductSupplies;
import com.gacortech.eprocurement.entity.Products;
import com.gacortech.eprocurement.entity.Vendors;
import com.gacortech.eprocurement.repository.ProductSuppliesRepository;
import com.gacortech.eprocurement.service.ProductSuppliesService;
import com.gacortech.eprocurement.specification.ProductSupplySpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductSuppliesServiceImpl implements ProductSuppliesService {

    private final ProductSuppliesRepository productSupplyRepository;
    private final ProductsServiceImpl productsService;
    private final VendorsServiceImpl vendorsService;
    @Override
    public List<ProductSupplyResponse> getAll(ProductSupply productSupply) {

        if(     productSupply.getId() == null &&
                productSupply.getProductId() == null &&
                productSupply.getVendorId() == null &&
                productSupply.getPrice() == null &&
                productSupply.getStock() == null
        ){
            return productSupplyRepository.findAll().stream().map(
                    detail -> ProductSupplyResponse.builder()
                            .id(detail.getId())
                            .productName(detail.getProduct().getName())
                            .vendorName(detail.getVendor().getNameVendor())
                            .price(detail.getPrice())
                            .stock(detail.getStock())
                            .build()
            ).toList();
        }

        if(productSupply.getId() != null){
            return productSupplyRepository.findById(productSupply.getId()).stream().map(
                    detail -> ProductSupplyResponse.builder()
                            .id(detail.getId())
                            .productName(detail.getProduct().getName())
                            .vendorName(detail.getVendor().getNameVendor())
                            .price(detail.getPrice())
                            .stock(detail.getStock())
                            .build()
            ).toList();
        }

        if(productSupply.getPrice() == null){
            productSupply.setPrice(Integer.MAX_VALUE);
        }

        if(productSupply.getStock() == null){
            productSupply.setStock(0);
        }

        Products products = null;
        Vendors vendors = null;


        if(productSupply.getProductId() != null){
              products = productsService.entityId(productSupply.getProductId());
        }

        if(productSupply.getVendorId() != null){
            vendors = vendorsService.entityById(productSupply.getVendorId());
        }

        List<ProductSupplyResponse> temp;

        if(productSupply.getVendorId() != null && productSupply.getProductId() != null){
            temp = productSupplyRepository
                    .findByVendorAndProduct(
                            vendors,
                            products
                    )
                    .filter(
                            detail ->
                                    detail.getStock() >= productSupply.getStock()
                            &&
                                    detail.getPrice() <= productSupply.getPrice()
                    )
                    .stream()
                    .map(
                            detail -> ProductSupplyResponse.builder()
                                    .id(detail.getId())
                                    .productName(detail.getProduct().getName())
                                    .vendorName(detail.getVendor().getNameVendor())
                                    .price(detail.getPrice())
                                    .stock(detail.getStock())
                                    .build()
                    ).toList();
        } else if (productSupply.getVendorId() != null || productSupply.getProductId() != null) {
            temp = productSupplyRepository
                    .findByVendorOrProduct(
                            vendors,
                            products
                    )
                    .filter(
                            detail ->
                                    detail.getStock() >= productSupply.getStock()
                                            &&
                                            detail.getPrice() <= productSupply.getPrice()
                    )
                    .stream()
                    .map(
                            detail -> ProductSupplyResponse.builder()
                                    .id(detail.getId())
                                    .productName(detail.getProduct().getName())
                                    .vendorName(detail.getVendor().getNameVendor())
                                    .price(detail.getPrice())
                                    .stock(detail.getStock())
                                    .build()
                    ).toList();
        }else{
            temp = productSupplyRepository
                    .findByPriceLessThan(productSupply.getPrice())
                    .filter(
                            detail -> detail.getStock() >= productSupply.getStock()
                    ).stream().map(
                            detail -> ProductSupplyResponse.builder()
                                    .id(detail.getId())
                                    .productName(detail.getProduct().getName())
                                    .vendorName(detail.getVendor().getNameVendor())
                                    .price(detail.getPrice())
                                    .stock(detail.getStock())
                                    .build()
                    ).toList();
        }

        if(temp.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND);
        }

        return temp;
    }

    @Override
    public ProductSupplies getByid(Integer i) {
        return productSupplyRepository.findById(i)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND)
                );
    }

    @Override
    public ProductSupplyResponse create(ProductSupply productSupply) {

        Products productFound = productsService.entityId(productSupply.getProductId());
        Vendors vendorFound = vendorsService.entityById(productSupply.getVendorId());

        Set<ProductSupplies> collect = productSupplyRepository.findAll().stream()
                .filter(productSupplies ->
                        productSupplies.getProduct().equals(productFound)
                                &&
                        productSupplies.getVendor().equals(vendorFound)

                )
                .collect(Collectors.toSet());

        if(!collect.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, ResponseMessages.ERROR_ALREADY_EXISTS);
        }

        ProductSupplies productSupplies = productSupplyRepository.saveAndFlush(
                ProductSupplies.builder()
                        .product(productFound)
                        .vendor(vendorFound)
                        .price(productSupply.getPrice())
                        .stock(productSupply.getStock())
                        .build()
        );

        return ProductSupplyResponse.builder()
                .id(productSupplies.getId())
                .productName(productSupplies.getProduct().getName())
                .vendorName(productSupplies.getVendor().getNameVendor())
                .price(productSupplies.getPrice())
                .stock(productSupply.getStock())
                .build();
    }

    @Override
    public ProductSupplyResponse update(ProductSupply productSupply) {
        getByid(productSupply.getId());
        Products productFound = productsService.entityId(productSupply.getProductId());
        Vendors vendorFound = vendorsService.entityById(productSupply.getVendorId());
        ProductSupplies temp = productSupplyRepository.saveAndFlush(
                ProductSupplies.builder()
                        .product(productFound)
                        .vendor(vendorFound)
                        .price(productSupply.getPrice())
                        .stock(productSupply.getStock())
                        .build()
        );
        return ProductSupplyResponse.builder()
                .id(temp.getId())
                .price(temp.getPrice())
                .stock(temp.getStock())
                .productName(temp.getProduct().getName())
                .vendorName(temp.getVendor().getNameVendor())
                .build();
    }

    @Override
    public ProductSupplyResponse responseById(Integer i) {
        ProductSupplies byid = getByid(i);
        return ProductSupplyResponse.builder()
                .id(byid.getId())
                .price(byid.getPrice())
                .stock(byid.getStock())
                .productName(byid.getProduct().getName())
                .vendorName(byid.getVendor().getNameVendor())
                .build();
    }
}
