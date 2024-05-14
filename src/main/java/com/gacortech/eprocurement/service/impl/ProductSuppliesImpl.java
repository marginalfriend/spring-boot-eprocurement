package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.entity.ProductSupplies;
import com.gacortech.eprocurement.repository.ProductSuppliesRepository;
import com.gacortech.eprocurement.service.ProductSuppliesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductSuppliesImpl implements ProductSuppliesService {

    private final ProductSuppliesRepository productSupplyRepository;
    @Override
    public List<ProductSupplies> getAll() {

        return productSupplyRepository.findAll();
    }

    @Override
    public ProductSupplies getByid(Integer i) {

        return productSupplyRepository.findById(i)
                .orElseThrow(
                        () -> new RuntimeException("Product Supply Not Found")
                );
    }

    @Override
    public ProductSupplies create(ProductSupply productSupply) {

        return productSupplyRepository.saveAndFlush(
                new ProductSupplies().builder()
                        .productId(productSupply.getProductId())
                        .vendorId(productSupply.getVendorId())
                        .price(productSupply.getPrice())
                        .stock(productSupply.getStock())
                        .build()
        );
    }



    @Override
    public ProductSupplies update(ProductSupply productSupply) {
        getByid(productSupply.getId());
        return productSupplyRepository.saveAndFlush(
                new ProductSupplies().builder()
                        .productId(productSupply.getProductId())
                        .vendorId(productSupply.getVendorId())
                        .price(productSupply.getPrice())
                        .stock(productSupply.getStock())
                        .build()
        );
    }
}
