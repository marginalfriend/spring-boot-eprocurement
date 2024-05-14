package com.gacortech.eprocurement.specification;

import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.entity.ProductSupplies;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ProductSupplySpecification {

    public static Specification<ProductSupplies> getSpecification(ProductSupply productSupply){

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Predicate prdctIdPred =  criteriaBuilder.equal(root.get("productId"), productSupply.getProductId());
            predicates.add(prdctIdPred);

            Predicate vendorId = criteriaBuilder.equal(root.get("vendor_id"), productSupply.getVendorId());
            predicates.add(vendorId);

            return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
