package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.entity.ProductSupply;
import com.gacortech.eprocurement.repository.ProductSupplyRepository;
import com.gacortech.eprocurement.service.ProductSupplyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductSupplyImpl implements ProductSupplyService {

    private final ProductSupplyRepository productSupplyRepository;
    @Override
    public List<ProductSupply> getAll() {

        return productSupplyRepository.findAll();
    }

    @Override
    public ProductSupply getByid(Integer i) {

        return productSupplyRepository.findById(i)
                .orElseThrow(
                        () -> new RuntimeException("Product Supply Not Found")
                );
    }

    @Override
    public ProductSupply create(ProductSupply productSupply) {

        return productSupplyRepository.saveAndFlush(productSupply);
    }

    @Override
    public ProductSupply update(ProductSupply productSupply) {
        getByid(productSupply.getId());
        return productSupplyRepository.saveAndFlush(productSupply);
    }
}
