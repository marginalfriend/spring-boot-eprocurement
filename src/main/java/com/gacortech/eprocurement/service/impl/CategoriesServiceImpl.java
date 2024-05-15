package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.Category;
import com.gacortech.eprocurement.dto.response.CategoryResponse;
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
    public CategoryResponse create(Category request) {
        Categories newCategory = Categories.builder()
                .name(request.getName())
                .build();
        categoriesRepository.saveAndFlush(newCategory);
        return CategoryResponse.builder()
                .id(newCategory.getId())
                .categoryName(newCategory.getName())
                .build();
    }

    @Override
    public CategoryResponse getById(Integer id) {
        Categories categoryFound = categoriesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND));
        return CategoryResponse.builder()
                .id(categoryFound.getId())
                .categoryName(categoryFound.getName())
                .build();
    }

    @Override
    public Categories entityById(Integer id) {
        return categoriesRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND));
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories.stream()
                .map(ctg -> CategoryResponse.builder()
                        .id(ctg.getId())
                        .categoryName(ctg.getName())
                        .build()).toList();
    }

    @Override
    public CategoryResponse update(Category category) {
        Categories updateCategory = entityById(category.getId());
        categoriesRepository.saveAndFlush(updateCategory);
        return CategoryResponse.builder()
                .id(category.getId())
                .categoryName(category.getName())
                .build();
    }

}
