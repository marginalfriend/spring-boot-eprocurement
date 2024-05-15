package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.dto.request.OrderRequest;
import com.gacortech.eprocurement.dto.response.OrderDetailResponse;
import com.gacortech.eprocurement.dto.response.OrdersResponse;
import com.gacortech.eprocurement.entity.OrderDetails;
import com.gacortech.eprocurement.entity.Orders;
import com.gacortech.eprocurement.entity.ProductSupplies;
import com.gacortech.eprocurement.repository.OrdersRepository;
import com.gacortech.eprocurement.repository.ProductSupplyRepository;
import com.gacortech.eprocurement.service.OrderDetailService;
import com.gacortech.eprocurement.service.OrdersService;
import com.gacortech.eprocurement.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private static final Logger log = LoggerFactory.getLogger(OrdersServiceImpl.class);
    private final OrdersRepository ordersRepository;
    private final ProductsService productSuppliesRepository;
    private final OrderDetailService orderDetailService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrdersResponse create(OrderRequest request){

        Orders order = Orders.builder()
                .orderDate(LocalDate.now())
                .build();

        ordersRepository.saveAndFlush(order);
        log.info("Check order details: {}", order.getOrderDate());


        List<OrderDetails> orderDetails = request.getOrderDetails().stream()
                .map(detail -> {
                    log.info("Quantity dari detail request: {}", detail.getQuantity());
                    ProductSupplies productSupplies = ProductSupplyRepository.getById(detail.getProductSupplyId());

                    if (productSupplies.getQuantity() < detail.getQuantity()) {
                        throw new IllegalArgumentException("Insufficient product quantity");
                    }

                    productSupplies.setQuantity(productSupplies.getQuantity() - detail.getQuantity());

                    return OrderDetails.builder()
                            .orders(order)
                            .productSupplies(productSupplies)
                            .quantity(detail.getQuantity())
                            .build();
                }).toList();
       ordersRepository.saveAndFlush(order);
       log.info("Check order details: {}", order.getOrderDate());


        List<OrderDetailResponse> savedOrderDetails = orderDetails.stream()
                .map(detail -> {
                    return OrderDetailResponse.builder()
                            .productId(detail.getId())
                            .productName(detail.getProductSupplies().getProduct().getName())
                            .quantity(detail.getQuantity())
                            .price(detail.getProductSupplies().getPrice())
                            .totalAmount(detail.getProductSupplies().getPrice() * detail.getQuantity())
                            .build();
                        }).toList();

        return OrdersResponse.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate().toString())
                .orderDetails(savedOrderDetails)
                .build();
    }

    @Override
    public List<OrdersResponse> getAllOrders() {
        List<Orders> orders = ordersRepository.findAll();

        return orders.stream().map(order -> {
            List<OrderDetailResponse> orderDetailResponse = order.getOrderDetails().stream()
                    .map(detail ->{
                        return OrderDetailResponse.builder()
                                .productId(detail.getId())
                                .productName(detail.getProductSupplies().getProduct().getName())
                                .quantity(detail.getQuantity())
                                .price(detail.getProductSupplies().getPrice())
                                .totalAmount(detail.getProductSupplies().getPrice() * detail.getQuantity())
                                .build();
                    }).toList();

            return OrdersResponse.builder()
                    .id(order.getId())
                    .orderDate(order.getOrderDate().toString())
                    .orderDetails(orderDetailResponse)
                    .build();

        }).toList();

    }
}

