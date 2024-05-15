package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.entity_rep.Category;
import com.gacortech.eprocurement.dto.response.CategoryResponse;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.entity.Categories;
import com.gacortech.eprocurement.service.CategoriesService;
import com.gacortech.eprocurement.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Routes.CATEGORIES)
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final ValidationUtil validationUtil;

    @PostMapping
    public ResponseEntity<CommonResponse<CategoryResponse>> addNewCategory(@RequestBody Category request){
        validationUtil.validate(request);
        CategoryResponse newCategory = categoriesService.create(request);
        CommonResponse<CategoryResponse> response = CommonResponse.<CategoryResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessages.SUCCESS_SAVE_DATA)
                .data(newCategory)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<CategoryResponse>>> showAllCategories(
            @RequestParam(name = "name", required = false) String name
    ){
        Category request = Category.builder()
                .name(name)
                .build();
        List<CategoryResponse> getCategory = categoriesService.getAll(request);
        CommonResponse<List<CategoryResponse>> response = CommonResponse.<List<CategoryResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(getCategory)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = Routes.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<CategoryResponse>> getCategoryById(@PathVariable Integer id){
        CategoryResponse getCategory = categoriesService.getById(id);
        CommonResponse<CategoryResponse> response = CommonResponse.<CategoryResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(getCategory)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<CategoryResponse>> updateCategories(@RequestBody Category request){
        validationUtil.validate(request);
        CategoryResponse updateCategory = categoriesService.update(request);
        CommonResponse<CategoryResponse> response = CommonResponse.<CategoryResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_UPDATE_DATA)
                .data(updateCategory)
                .build();
        return ResponseEntity.ok(response);
    }
}
