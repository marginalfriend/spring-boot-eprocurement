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
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailsRepository orderDetailsRepository;

    @Override
    public List<OrderDetails> createBulk(List<OrderDetails> orderDetails) {
        return orderDetailsRepository.saveAll(orderDetails);
    }
}
