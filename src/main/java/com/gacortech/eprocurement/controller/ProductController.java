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
import com.gacortech.eprocurement.utils.ValidationUtil;
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
    private final ValidationUtil validationUtil;

    @PostMapping
    public ResponseEntity<CommonResponse<ProductResponse>> addNewProduct(@RequestBody Product request){
        validationUtil.validate(request);
        ProductResponse newProduct = productsService.create(request);
        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessages.SUCCESS_SAVE_DATA)
                .data(newProduct)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<ProductResponse>>> showAllProduct(
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

        List<ProductResponse> productResponses = allProduct.getContent().stream()
                .map(prd -> new ProductResponse(
                        prd.getId(),
                        prd.getName(),
                        prd.getCategory().getId()
                )).toList();

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(allProduct.getTotalPages())
                .totalElements(allProduct.getTotalElements())
                .page(allProduct.getPageable().getPageNumber())
                .size(allProduct.getPageable().getPageSize())
                .hasNext(allProduct.hasNext())
                .hasPrevious(allProduct.hasPrevious())
                .build();

        CommonResponse<List<ProductResponse>> response = CommonResponse.<List<ProductResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(productResponses)
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = Routes.PATH_VAR_ID)
    public ResponseEntity<CommonResponse<ProductResponse>> getProductById(@PathVariable String id){
        ProductResponse getProduct = productsService.getById(id);
        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(getProduct)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<ProductResponse>> updateProduct(@RequestBody Product request){
        validationUtil.validate(request);
        ProductResponse updateProduct = productsService.update(request);
        CommonResponse<ProductResponse> response = CommonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_UPDATE_DATA)
                .data(updateProduct)
                .build();
        return ResponseEntity.ok(response);
    }
}
