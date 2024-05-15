package com.gacortech.eprocurement.specification;

import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.entity.ProductSupplies;
import com.gacortech.eprocurement.entity.Vendors;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ProductSupplySpecification {

    public static Specification<ProductSupplies> getVendorAndProductEqual(ProductSupply productSupply){

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(productSupply.getStock() != null){
                Predicate stock = criteriaBuilder.greaterThanOrEqualTo(root.get("stock"), productSupply.getStock());
                predicates.add(stock);
            }

            return query.where(criteriaBuilder.and(predicates.toArray(new Predicate[]{}))).getRestriction();
        };
    }
}
