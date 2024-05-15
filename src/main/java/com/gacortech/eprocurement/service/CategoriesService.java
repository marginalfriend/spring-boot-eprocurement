package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.entity_rep.Category;
import com.gacortech.eprocurement.dto.response.CategoryResponse;
import com.gacortech.eprocurement.entity.Categories;

import java.util.List;

public interface CategoriesService {
    CategoryResponse create(Category request);
    CategoryResponse getById(Integer id);
    Categories entityById(Integer id);
    List<CategoryResponse> getAll();
    CategoryResponse update(Category request);
}
