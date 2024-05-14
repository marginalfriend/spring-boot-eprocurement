package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.entity.Orders;
import com.gacortech.eprocurement.repository.OrdersRepository;
import com.gacortech.eprocurement.service.OrdersService;
import com.gacortech.eprocurement.service.ProductsService;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;



    @Override
    public void createOrder(Orders order) {

    }

    @Override
    public Orders getOrderById(String id) {
        return (Orders) ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<Order> getAllOrders() {
        return ordersRepository.findAll();
    }
}
