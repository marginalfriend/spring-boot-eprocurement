package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.entity.Categories;
import com.gacortech.eprocurement.repository.CategoriesRepository;
import com.gacortech.eprocurement.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Override
    public Categories create(Categories categories) {
        return categoriesRepository.saveAndFlush(categories);
    }

    @Override
    public Categories getById(Integer id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public List<Categories> getAll() {
        return categoriesRepository.findAll();
    }

    @Override
    public Categories update(Categories categories) {
        findByIdOrThrowNotFound(categories.getId());
        return categoriesRepository.saveAndFlush(categories);
    }

    public Categories findByIdOrThrowNotFound(Integer id){
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("category not found"));
    }
}
