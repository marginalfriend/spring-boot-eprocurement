package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.entity_rep.ProductSupply;
import com.gacortech.eprocurement.dto.entity_rep.Vendor;
import com.gacortech.eprocurement.dto.response.ProductSupplyResponse;
import com.gacortech.eprocurement.service.impl.ProductSuppliesServiceImpl;
import com.gacortech.eprocurement.service.impl.VendorsServiceImpl;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Routes.VENDORS)
public class VendorsController {

    public final VendorsServiceImpl vendorsService;
    public final ProductSuppliesServiceImpl prodSuppSer;
    //Get all Vendors
    @GetMapping
    public List<Vendor> getAllVendors(
            @RequestParam(name = "nameVendor", required = false) String name
    ){
        return vendorsService.getAll(name);
    }

    //Get Supply By Vendor id
    @GetMapping(path = "/product-supplies")
    public List<ProductSupplyResponse> findSupplyByVendor(
            @RequestParam(name = "supplyId", required = false) Integer supplyId,
            @RequestParam(name = "productId", required = false) String productId,
            @RequestParam(name = "vendorId", required = false) String vendorId,
            @RequestParam(name = "price", required = false) Integer price,
            @RequestParam(name = "stock", required = false) Integer stock
    ){

        return prodSuppSer.getAll(ProductSupply.builder()
                .id(supplyId)
                .productId(productId)
                .vendorId(vendorId)
                .price(price)
                .stock(stock)
                .build());
    }

    @PutMapping
    public Vendor updateVendor(@RequestBody Vendor v){
        return vendorsService.update(v);
    }

    @PostMapping
    public Vendor createVendor(@RequestBody Vendor v){
        return vendorsService.create(v);
    }

    @PostMapping(path = "/product-supplies")
    public ProductSupplyResponse addProduct(
            @RequestBody ProductSupply productSupply
    ){
        return prodSuppSer.create(productSupply);
    }


}
