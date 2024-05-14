package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.entity.Orders;
import jakarta.persistence.criteria.Order;

import java.util.List;

public interface OrdersService {
    void createOrder(Orders order);

    Orders getOrderById(String id);

    List<Order> getAllOrders();
}
