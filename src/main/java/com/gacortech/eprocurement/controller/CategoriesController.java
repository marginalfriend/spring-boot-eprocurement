package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.entity_rep.Category;
import com.gacortech.eprocurement.dto.response.CategoryResponse;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.entity.Categories;
import com.gacortech.eprocurement.service.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v0/categories")
public class CategoriesController {
    private final CategoriesService categoriesService;

    @PostMapping
    public CategoryResponse addNewCategory(@RequestBody Category request){
        return categoriesService.create(request);
    }

    @GetMapping
    public List<CategoryResponse> showAllCategories(){
        return categoriesService.getAll();
    }

    @GetMapping(path = "/{id}")
    public Categories getCategoryById(@PathVariable Integer id){
        return categoriesService.getById(id);
    }

    @PutMapping
    public CategoryResponse updateCategories(@RequestBody Category request){
        return categoriesService.update(request);
    }
}
