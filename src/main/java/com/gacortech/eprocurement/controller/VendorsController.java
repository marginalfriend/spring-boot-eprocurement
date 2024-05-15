package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.dto.entity_rep.Vendor;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.dto.response.ProductSupplyResponse;
import com.gacortech.eprocurement.service.impl.ProductSuppliesServiceImpl;
import com.gacortech.eprocurement.service.impl.VendorsServiceImpl;
import com.gacortech.eprocurement.utils.ValidationUtil;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Routes.VENDORS)
public class VendorsController {

    public final VendorsServiceImpl vendorsService;
    public final ProductSuppliesServiceImpl prodSuppSer;
    public final ValidationUtil validationUtil;
    //Get all Vendors
    @GetMapping
    public ResponseEntity<CommonResponse<List<Vendor>>> getAllVendors(
            @RequestParam(name = "nameVendor", required = false) String name
    ){
        List<Vendor> all = vendorsService.getAll(name);
        CommonResponse<List<Vendor>> build = CommonResponse.<List<Vendor>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(all)
                .build();

        return ResponseEntity
                .ok(build);
    }

    //Get Supply By Vendor id
    @GetMapping(path = "/product-supplies")
    public ResponseEntity<CommonResponse<List<ProductSupplyResponse>>> findSupplyByVendor(
            @RequestParam(name = "supplyId", required = false) Integer supplyId,
            @RequestParam(name = "productId", required = false) String productId,
            @RequestParam(name = "vendorId", required = false) String vendorId,
            @RequestParam(name = "price", required = false) Integer price,
            @RequestParam(name = "stock", required = false) Integer stock
    ){
        List<ProductSupplyResponse> all = prodSuppSer.getAll(ProductSupply.builder()
                .id(supplyId)
                .productId(productId)
                .vendorId(vendorId)
                .price(price)
                .stock(stock)
                .build());
        CommonResponse<List<ProductSupplyResponse>> build = CommonResponse.<List<ProductSupplyResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(all)
                .build();
        return ResponseEntity.ok(build);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<Vendor>> updateVendor(@RequestBody Vendor v){
        Vendor update = vendorsService.update(v);
        CommonResponse<Vendor> build = CommonResponse.<Vendor>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_UPDATE_DATA)
                .data(update)
                .build();
        return ResponseEntity.ok(build);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<Vendor>> createVendor(@RequestBody Vendor v){
        validationUtil.validate(v);
        Vendor vendor = vendorsService.create(v);
        CommonResponse<Vendor> build = CommonResponse.<Vendor>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessages.SUCCESS_UPDATE_DATA)
                .data(vendor)
                .build();
        return ResponseEntity.ok(build);
    }

    @PostMapping(path = "/product-supplies")
    public ResponseEntity<CommonResponse<ProductSupplyResponse>> addProduct(
            @RequestBody ProductSupply productSupply
    ){
        ProductSupplyResponse productSupplyResponse = prodSuppSer.create(productSupply);
        CommonResponse<ProductSupplyResponse> build = CommonResponse.<ProductSupplyResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ResponseMessages.SUCCESS_UPDATE_DATA)
                .data(productSupplyResponse)
                .build();
        return ResponseEntity.ok(build);
    }


}
