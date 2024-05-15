package com.gacortech.eprocurement.repository;

import com.gacortech.eprocurement.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, String> {



}
