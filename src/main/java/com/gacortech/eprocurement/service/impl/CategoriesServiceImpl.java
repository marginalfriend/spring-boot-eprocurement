package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.Category;
import com.gacortech.eprocurement.entity.Categories;
import com.gacortech.eprocurement.repository.CategoriesRepository;
import com.gacortech.eprocurement.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {
    private final CategoriesRepository categoriesRepository;

    @Override
    public Categories create(Category category) {
        Categories newCategory = Categories.builder()
                .name(category.getName())
                .build();
        return categoriesRepository.saveAndFlush(newCategory);
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
    public Categories update(Category category) {
        findByIdOrThrowNotFound(category.getId());
        Categories updateCategory = Categories.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoriesRepository.saveAndFlush(updateCategory);
    }

    public Categories findByIdOrThrowNotFound(Integer id){
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND));
    }
}
