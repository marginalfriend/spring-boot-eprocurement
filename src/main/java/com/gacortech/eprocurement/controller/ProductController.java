package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.entity_rep.Product;
import com.gacortech.eprocurement.dto.request.SearchProductRequest;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.dto.response.PagingResponse;
import com.gacortech.eprocurement.dto.response.ProductResponse;
import com.gacortech.eprocurement.entity.Products;
import com.gacortech.eprocurement.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Routes.PRODUCTS)
public class ProductController {
    private final ProductsService productsService;

    @PostMapping
    public ProductResponse addNewProduct(@RequestBody Product request){
        return productsService.create(request);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Products>>> showAllProduct(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "categoryId", required = false) Integer categoryId,
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ){
        SearchProductRequest request = SearchProductRequest.builder()
                .name(name)
                .categoryId(categoryId)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .direction(direction)
                .build();

        Page<Products> allProduct = productsService.getAll(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allProduct.getTotalPages())
                .totalElements(allProduct.getTotalElements())
                .page(allProduct.getPageable().getPageNumber())
                .size(allProduct.getPageable().getPageSize())
                .hasNext(allProduct.hasNext())
                .hasPrevious(allProduct.hasPrevious())
                .build();

        CommonResponse<List<Products>> response = CommonResponse.<List<Products>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(allProduct.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ProductResponse getProductById(@PathVariable String id){
        return productsService.getById(id);
    }

    @PutMapping
    public ProductResponse updateProduct(@RequestBody Product request){
        return productsService.update(request);
    }
}
