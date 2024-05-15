package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.request.OrderRequest;
import com.gacortech.eprocurement.dto.request.SearchOrderRequest;
import com.gacortech.eprocurement.dto.response.OrdersResponse;
import com.gacortech.eprocurement.entity.Orders;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrdersService {
    OrdersResponse create(OrderRequest request);
    Page<Orders> getAllOrders(SearchOrderRequest request);
    OrdersResponse getById(String id);
    List<Orders> getAllOrders(String orderDate, String customerName, String status, Double totalAmount);
}
