package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.request.ReportRequest;
import com.gacortech.eprocurement.dto.request.SearchOrderRequest;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.dto.response.PagingResponse;
import com.gacortech.eprocurement.dto.response.ReportResponse;
import com.gacortech.eprocurement.entity.Orders;
import com.gacortech.eprocurement.entity.ProductSupplies;
import com.gacortech.eprocurement.entity.Vendors;
import com.gacortech.eprocurement.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    OrdersService           ordersService;
    @Autowired
    ProductSuppliesService  suppliesService;
    @Override
    public CommonResponse<List<ReportResponse>> getAll(ReportRequest request) {
        SearchOrderRequest searchOrderRequest;

        if (request.getDate() != null && request.getMonth() != null) {
            throw new RuntimeException(ResponseMessages.ERROR_DATE_AND_MONTH);

        } else if (request.getDate() != null) {
        searchOrderRequest = SearchOrderRequest.builder()
                .date(request.getDate())
                .size(Integer.MAX_VALUE)
                .build();

        } else {
        searchOrderRequest = SearchOrderRequest.builder()
                .month(request.getMonth())
                .size(Integer.MAX_VALUE)
                .build();
        }


        Page<Orders> orders = ordersService.getAllOrders(searchOrderRequest);
        List<ReportResponse> result = new ArrayList<>();

        orders.getContent().forEach(order -> {
            
            order.getOrderDetails().forEach(detail -> {
                
                ProductSupplies supply = suppliesService.getByid(detail.getProductSupplies().getId());
                Vendors vendor = supply.getVendor();
                
                result.add(
                        ReportResponse.builder()
                                .id(detail.getId())
                                .productName(supply.getProduct().getName())
                                .vendorName(vendor.getNameVendor())
                                .purchaseDate(order.getOrderDate().toString())
                                .quantity(detail.getQuantity())
                                .totalPrice(detail.getProductSupplies().getPrice() * detail.getQuantity())
                                .build()
                );
            });
        });

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(orders.getTotalPages())
                .totalElements(orders.getTotalElements())
                .page(orders.getPageable().getPageNumber())
                .size(orders.getSize())
                .hasNext(orders.hasNext())
                .hasPrevious(orders.hasPrevious())
                .build();

        return CommonResponse.<List<ReportResponse>>builder()
                .data(result)
                .paging(pagingResponse)
                .build();
    }
}
