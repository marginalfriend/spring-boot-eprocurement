package com.gacortech.eprocurement.specification;

import com.gacortech.eprocurement.dto.request.OrderRequest;
import com.gacortech.eprocurement.dto.request.SearchOrderRequest;
import com.gacortech.eprocurement.dto.response.OrdersResponse;
import com.gacortech.eprocurement.entity.Orders;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderSpecification {

    public static Specification<Orders> getSpecification(SearchOrderRequest searchOrderRequest) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();
        if (searchOrderRequest.getDate() != null){
            Predicate datePredicate = criteriaBuilder.equal(root.get("date"),searchOrderRequest.getDate());
            predicates.add(datePredicate);
    }

        if (searchOrderRequest.getMonth() != null){
            Predicate monthPredicate = criteriaBuilder.equal(root.get("month"),searchOrderRequest.getMonth());
            predicates.add(monthPredicate);

        }
        return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
    };

    }
}
