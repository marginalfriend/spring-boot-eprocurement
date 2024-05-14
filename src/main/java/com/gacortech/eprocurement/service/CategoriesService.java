package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.entity_rep.Category;
import com.gacortech.eprocurement.entity.Categories;

import java.util.List;

public interface CategoriesService {
    Categories create(Category category);
    Categories getById(Integer id);
    List<Categories> getAll();
    Categories update(Category category);
}
