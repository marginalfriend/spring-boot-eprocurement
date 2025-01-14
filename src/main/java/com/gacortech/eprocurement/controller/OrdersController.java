package com.gacortech.eprocurement.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.request.OrderRequest;
import com.gacortech.eprocurement.dto.request.SearchOrderRequest;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.dto.response.OrdersResponse;
import com.gacortech.eprocurement.dto.response.PagingResponse;
import com.gacortech.eprocurement.entity.Orders;
import com.gacortech.eprocurement.service.OrdersService;
import com.gacortech.eprocurement.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = Routes.ORDERS)
public class OrdersController {
    private final OrdersService ordersService;
    private final ValidationUtil validationUtil;

  @PostMapping
    public ResponseEntity<CommonResponse<OrdersResponse>> createNewOrder(@RequestBody OrderRequest request)
  {
      validationUtil.validate(request);
      OrdersResponse orderResponse = ordersService.create(request);
      CommonResponse<OrdersResponse> response = CommonResponse.<OrdersResponse>builder()
              .statusCode(HttpStatus.CREATED.value())
              .message(ResponseMessages.SUCCESS_SAVE_DATA)
              .data(orderResponse)
              .build();
      return ResponseEntity .status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CommonResponse<OrdersResponse>> getOrderById(@PathVariable String id) {
        OrdersResponse orderResponse = ordersService.getById(id);
        CommonResponse<OrdersResponse> response = CommonResponse.<OrdersResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(orderResponse)
                .build();
        return ResponseEntity.ok(response);
    }




    @GetMapping
    public ResponseEntity<CommonResponse< List<Orders>>> getAllOrders(
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "month", required = false) String month,
            @RequestParam(name = "sortBy",required = false) String sortBy,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ) {


        SearchOrderRequest request = SearchOrderRequest.builder()
                .date(date)
                .month(month)
                .sortBy(sortBy)
                .direction(direction)
                .page(page)
                .size(size)
                .build();
        Page<Orders> ordersAll = ordersService.getAllOrders(request);

        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(ordersAll.getTotalPages())
                .totalElements(ordersAll.getTotalElements())
                .page(ordersAll.getPageable().getPageNumber() + 1)
                .size(ordersAll.getPageable().getPageSize())
                .hasNext(ordersAll.hasNext())
                .hasPrevious(ordersAll.hasPrevious())
                .build();

        CommonResponse<List<Orders>> response = CommonResponse.<List<Orders>>builder()
                .statusCode(HttpStatus.OK.value())
                .message(ResponseMessages.SUCCESS_GET_DATA)
                .data(ordersAll.getContent())
                .paging(pagingResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}