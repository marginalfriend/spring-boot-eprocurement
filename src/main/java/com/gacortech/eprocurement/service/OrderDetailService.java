package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.entity.OrderDetails;


import java.util.List;

public interface OrderDetailService {

    void createOrderDetail(OrderDetails orderDetail);

    OrderDetails getOrderDetailById(String id);

    List<OrderDetails> getAllOrderDetails();

}
