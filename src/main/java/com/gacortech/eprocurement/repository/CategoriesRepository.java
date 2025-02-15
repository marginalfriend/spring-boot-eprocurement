package com.gacortech.eprocurement.repository;

import com.gacortech.eprocurement.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer>, JpaSpecificationExecutor<Categories> {
}
