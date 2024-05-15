package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.request.OrderRequest;
import com.gacortech.eprocurement.dto.response.OrdersResponse;

import java.util.List;

public interface OrdersService {
    OrdersResponse create(OrderRequest request);
    List<OrdersResponse> getAllOrders();

}
