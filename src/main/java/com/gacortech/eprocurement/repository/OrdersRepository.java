package com.gacortech.eprocurement.repository;

import com.gacortech.eprocurement.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrdersRepository extends JpaRepository<Orders, String> {


    Page<Orders> findAll(Specification<Orders> specification, Pageable pageable);
}
