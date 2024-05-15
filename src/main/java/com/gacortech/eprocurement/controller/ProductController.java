package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.entity_rep.Product;
import com.gacortech.eprocurement.dto.response.ProductResponse;
import com.gacortech.eprocurement.service.ProductsService;
import lombok.RequiredArgsConstructor;
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
    public List<ProductResponse> showAllProduct(){
        return productsService.getAll();
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
