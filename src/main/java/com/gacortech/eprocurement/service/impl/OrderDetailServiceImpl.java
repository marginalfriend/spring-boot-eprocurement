package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.entity.OrderDetails;
import com.gacortech.eprocurement.repository.OrderDetailsRepository;
import com.gacortech.eprocurement.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class    OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public void createOrderDetail(OrderDetails orderDetail) {
        orderDetailsRepository.save(orderDetail);
    }

    @Override
    public OrderDetails getOrderDetailById(String id) {
        Optional<OrderDetails> optionalOrderDetail = orderDetailsRepository.findById(id);
        return optionalOrderDetail.orElseThrow(() -> new RuntimeException("Order detail not found"));
    }

    @Override
    public List<OrderDetails> getAllOrderDetails() {
      List<OrderDetails> orderDetails = orderDetailsRepository.findAll();
        if (orderDetails.isEmpty()) {
            throw new RuntimeException("No order details found");
        }
        return orderDetailsRepository.findAll();
    }
}
