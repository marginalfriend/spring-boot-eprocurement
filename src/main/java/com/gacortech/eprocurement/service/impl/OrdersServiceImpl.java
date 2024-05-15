package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.dto.entity_rep.OrderDetail;
import com.gacortech.eprocurement.dto.request.OrderRequest;
import com.gacortech.eprocurement.dto.request.SearchOrderRequest;
import com.gacortech.eprocurement.dto.response.OrderDetailResponse;
import com.gacortech.eprocurement.dto.response.OrdersResponse;
import com.gacortech.eprocurement.entity.OrderDetails;
import com.gacortech.eprocurement.entity.Orders;
import com.gacortech.eprocurement.entity.ProductSupplies;
import com.gacortech.eprocurement.repository.OrdersRepository;
import com.gacortech.eprocurement.service.OrderDetailService;
import com.gacortech.eprocurement.service.OrdersService;
import com.gacortech.eprocurement.service.ProductSuppliesService;
import com.gacortech.eprocurement.specification.OrderSpecification;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDate;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private static final Logger log = LoggerFactory.getLogger(OrdersServiceImpl.class);
    private final OrdersRepository ordersRepository;
    private final OrderDetailService orderDetailService;
    private final ProductSuppliesService productSuppliesService;
    private  final EntityManager entityManager;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrdersResponse create(OrderRequest request){

        Orders order = Orders.builder()
                .orderDate(new Timestamp(new Date().getTime()))
                .build();

        ordersRepository.saveAndFlush(order);
        log.info("Check order details: {}", order.getOrderDate());

        List<OrderDetails> orderDetails = request.getOrderDetails().stream()
                .map(detail -> {
                    log.info("Quantity dari detail request: {}", detail.getQuantity());
                    ProductSupplies productSupplies = productSuppliesService.getByid(detail.getProductSupplyId());


                    productSupplies.setStock(productSupplies.getStock() + detail.getQuantity());

                    return OrderDetails.builder()
                            .orders(order)
                            .productSupplies(productSupplies)
                            .quantity(detail.getQuantity())
                            .build();
                }).toList();
        orderDetailService.createBulk(orderDetails);
        ordersRepository.saveAndFlush(order);
        log.info("Check order details: {}", order.getOrderDate());


        List<OrderDetailResponse> savedOrderDetails = orderDetails.stream()
                .map(detail -> OrderDetailResponse.builder()
                        .id(detail.getId())
                        .supplyId(detail.getProductSupplies().getId())
                        .productName(detail.getProductSupplies().getProduct().getName())
                        .quantity(detail.getQuantity())
                        .price(detail.getPrice())
                        .totalAmount(detail.getPrice() * detail.getQuantity())
                        .build()).toList();

        return OrdersResponse.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate().toString())
                .orderDetails(savedOrderDetails)
                .totalAmount(orderDetails.stream().mapToInt(OrderDetails::getPrice).sum())
                .build();
    }

    @Override
    public Page<Orders> getAllOrders(SearchOrderRequest request) {
        String validSortBy;
        String validDirection;

        int page;
        if (request.getPage() == null) {
            page = 1;
        } else if (request.getPage() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Page number cannot be less than 1");
        } else {
            page = request.getPage();
        }

        if ("Date".equalsIgnoreCase(request.getSortBy()) || "Amount".equalsIgnoreCase(request.getSortBy())) {
            validSortBy = request.getSortBy();
            if (request.getDirection() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Direction is required");
            } else if (!request.getDirection().equals("asc") && !request.getDirection().equals("desc")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Direction must be asc or desc");
            } else {
                validDirection = request.getDirection();
            }
        } else {
            validDirection = "asc";
            validSortBy = "orderDate";
        }

        int size = 50;
        if (request.getSize()!= null){
            size = request.getSize();

        }

        Sort sort = Sort.by(Sort.Direction.fromString(validDirection), validSortBy);

        Pageable pageable = PageRequest.of((page - 1), size, sort);


        Specification<Orders> specification = OrderSpecification.getSpecification(request);
        return ordersRepository.findAll(specification, pageable);
    }

    @Override
    public OrdersResponse getById(String id) {
        Orders orders = ordersRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, ResponseMessages.ERROR_NOT_FOUND)
        );

        List<OrderDetailResponse> orderDetailResponses = orders.getOrderDetails().stream().map(details -> OrderDetailResponse.builder()
                .id(details.getId())
                .quantity(details.getQuantity())
                .productName(details.getProductSupplies().getProduct().getName())
                .price(details.getPrice())
                .supplyId(details.getProductSupplies().getId())
                .totalAmount(details.getPrice() * details.getQuantity())
                .build())
                .toList();

        Integer totalAmount = orders.getOrderDetails().stream().mapToInt(OrderDetails::getPrice).sum();
        return OrdersResponse.builder()
                .id(id)
                .orderDate(String.valueOf(orders.getOrderDate()))
                .totalAmount(totalAmount)
                .orderDetails(orderDetailResponses)
                .build();
    }

    }