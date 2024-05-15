package com.gacortech.eprocurement.repository;

import com.gacortech.eprocurement.entity.ProductSupplies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSuppliesRepository extends JpaRepository<ProductSupplies, Integer>, JpaSpecificationExecutor<ProductSupplies> {


}
