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
            if (searchOrderRequest.getMinAmount()!= null){
                Predicate minPredicate = criteriaBuilder.equal(root.get("minAmount"), searchOrderRequest.getMinAmount());
                predicates.add(minPredicate);
            }

            if (searchOrderRequest.getMaxAmount() != null) {
                Predicate maxPredicate = criteriaBuilder.equal(root.get("maxAmount"), searchOrderRequest.getMaxAmount());
                predicates.add(maxPredicate);

            }

            if(searchOrderRequest.getStartFrom()!= null) {
                Predicate startFrom = criteriaBuilder.greaterThanOrEqualTo(root.get("StartFrom"), searchOrderRequest.getStartFrom());
                predicates.add(startFrom);
            }
            if (searchOrderRequest.getEndTo() != null){
                Predicate endTo = criteriaBuilder.lessThanOrEqualTo(root.get("EndFrom"),searchOrderRequest.getEndTo());
                predicates.add(endTo);
            }
            if (searchOrderRequest.getDate() != null){
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parseDate = LocalDate.parse(searchOrderRequest.getDate(), dateTimeFormatter);
                Predicate datePredicate = criteriaBuilder.equal(root.get("Date"),parseDate);
                predicates.add(datePredicate);

            }
    return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };

    }
}
