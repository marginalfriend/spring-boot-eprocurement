package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.entity.OrderDetails;


import java.util.Collection;
import java.util.List;

public interface OrderDetailService {

    List<OrderDetails> createBulk(List<OrderDetails> orderDetails);
    OrderDetails getById(Integer id);
}
