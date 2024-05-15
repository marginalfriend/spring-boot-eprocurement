package com.gacortech.eprocurement.repository;

import com.gacortech.eprocurement.entity.ProductSupplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSuppliesRepository extends JpaRepository<ProductSupplies, Integer>, JpaSpecificationExecutor<ProductSupplies> {

    List<ProductSupplies> findAllByPriceLessThanAndStockGreaterThanEqual(Integer price, Integer stock);
}
