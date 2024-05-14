package com.gacortech.eprocurement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSupplyRepository extends JpaRepository<ProductSupplyRepository, Integer>, JpaSpecificationExecutor<ProductSupplies> {
}
