package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
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
    public List<ProductSupplies> getAll() {

        return productSupplyRepository.findAll();
    }

    @Override
    public ProductSupplies getByid(Integer i) {
        return productSupplyRepository.findById(i)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND)
                );
    }

    @Override
    public ProductSupplies create(ProductSupply productSupply) {

        Products productFound = productsService.findByIdOrThrowNotFound(productSupply.getProductId());
        Vendors vendorFound = vendorsService.getById(productSupply.getVendorId());

        Set<ProductSupplies> collect = getAll().stream()
                .filter(productSupplies ->
                        productSupplies.getProduct().equals(productFound)
                                &&
                        productSupplies.getVendor().equals(vendorFound)

                )
                .collect(Collectors.toSet());

        if(!collect.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, ResponseMessages.ERROR_ALREADY_EXISTS);
        }

        return productSupplyRepository.saveAndFlush(
                ProductSupplies.builder()
                        .product(productFound)
                        .vendor(vendorFound)
                        .price(productSupply.getPrice())
                        .stock(productSupply.getStock())
                        .build()
        );
    }



    @Override
    public ProductSupplies update(ProductSupply productSupply) {
        getByid(productSupply.getId());
        Products productFound = productsService.findByIdOrThrowNotFound(productSupply.getProductId());
        Vendors vendorFound = vendorsService.getById(productSupply.getVendorId());
        return productSupplyRepository.saveAndFlush(
                ProductSupplies.builder()
                        .product(productFound)
                        .vendor(vendorFound)
                        .price(productSupply.getPrice())
                        .stock(productSupply.getStock())
                        .build()
        );
    }
}
