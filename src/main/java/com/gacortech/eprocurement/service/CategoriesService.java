package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.entity.Categories;

import java.util.List;

public interface CategoriesService {
    Categories create(Categories categories);
    Categories getById(Integer id);
    List<Categories> getAll();
    Categories update(Categories categories);
}
