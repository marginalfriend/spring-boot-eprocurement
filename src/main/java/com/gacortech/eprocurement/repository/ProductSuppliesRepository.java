package com.gacortech.eprocurement.repository;

import com.gacortech.eprocurement.entity.ProductSupplies;
import com.gacortech.eprocurement.entity.Products;
import com.gacortech.eprocurement.entity.Vendors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSuppliesRepository extends JpaRepository<ProductSupplies, Integer>, JpaSpecificationExecutor<ProductSupplies> {

    Streamable<ProductSupplies> findByVendorAndProduct(Vendors vendor, Products product);
    Streamable<ProductSupplies> findByVendorOrProduct(Vendors vendor, Products product);
}
