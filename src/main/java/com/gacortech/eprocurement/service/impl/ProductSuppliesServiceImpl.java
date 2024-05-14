package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.entity.ProductSupplies;
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

@Service
@AllArgsConstructor
public class ProductSuppliesServiceImpl implements ProductSuppliesService {

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

        Specification<ProductSupplies> specification = ProductSupplySpecification.getSpecification(productSupply);
        Optional<ProductSupplies> one = productSupplyRepository.findOne(specification);

        if(one.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Product Supply Has Been Input");
        }

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
