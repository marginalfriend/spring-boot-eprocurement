package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.request.ReportRequest;
import com.gacortech.eprocurement.dto.request.SearchOrderRequest;
import com.gacortech.eprocurement.dto.response.OrdersResponse;
import com.gacortech.eprocurement.dto.response.ReportResponse;
import com.gacortech.eprocurement.entity.ProductSupplies;
import com.gacortech.eprocurement.entity.Vendors;
import com.gacortech.eprocurement.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    OrdersService           ordersService;
    ProductSuppliesService  suppliesService;
    @Override
    public List<ReportResponse> getAll(ReportRequest request) {
        SearchOrderRequest searchOrderRequest;

        if (request.getDate() != null && request.getMonth() != null) {
            throw new RuntimeException(ResponseMessages.ERROR_DATE_AND_MONTH);
        } else if (request.getDate() != null) {
        searchOrderRequest = SearchOrderRequest.builder()
                .Date(request.getDate())
                .build();
        } else {
            searchOrderRequest = SearchOrderRequest.builder()
                    .
                    .build();
        }


        List<OrdersResponse> orders = ordersService.getAllOrders();// Insert condition soon

        List<ReportResponse> result = new ArrayList<>();

        orders.forEach(order -> {

            order.getOrderDetails().forEach(detail -> {
                ProductSupplies products = suppliesService.getByid(detail.getSupplyId());
                Vendors vendor = products.getVendor();

                result.add(
                        ReportResponse.builder()
                                .id(detail.getId())
                                .productName(detail.getProductName())
                                .vendorName(vendor.getNameVendor())
                                .purchaseDate(order.getOrderDate())
                                .quantity(detail.getQuantity())
                                .totalPrice(detail.getPrice() * detail.getQuantity())
                                .build()
                );
            });
        });

        return result;
    }
}
