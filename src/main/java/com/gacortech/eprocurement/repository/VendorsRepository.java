package com.gacortech.eprocurement.repository;

import com.gacortech.eprocurement.entity.Vendors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorsRepository extends JpaRepository<Vendors, String>, JpaSpecificationExecutor<Vendors> {
}
