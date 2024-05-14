package com.gacortech.eprocurement.repository;

import com.gacortech.eprocurement.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, String>, JpaSpecificationExecutor<Products> {
}
