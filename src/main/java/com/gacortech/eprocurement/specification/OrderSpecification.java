package com.gacortech.eprocurement.specification;

import com.gacortech.eprocurement.dto.request.OrderRequest;
import com.gacortech.eprocurement.entity.Orders;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;

public class OrderSpecification {

    public static Specification<Orders> getSpecification(OrderRequest request) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("customerName"), "%" +  + "%");
    }
    public static Specification<Orders> orderDateBetween(LocalDate startDate, LocalDate endDate) {

        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("orderDate"), startDate, endDate);
    }


    public static Specification<Orders> orderDateEquals(LocalDate date) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("orderDate"), date);
    }
}
