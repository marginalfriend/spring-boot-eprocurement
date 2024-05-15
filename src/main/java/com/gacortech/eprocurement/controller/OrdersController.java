package com.gacortech.eprocurement.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.request.OrderRequest;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.dto.response.OrdersResponse;
import com.gacortech.eprocurement.dto.response.PagingResponse;
import com.gacortech.eprocurement.entity.Orders;
import com.gacortech.eprocurement.service.OrdersService;
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

  @PostMapping
    public ResponseEntity<CommonResponse<OrdersResponse>> createNewOrder(@RequestBody OrderRequest request)
  {
      OrdersResponse orderResponse = ordersService.create(request);
      CommonResponse<OrdersResponse> response = CommonResponse.<OrdersResponse>builder()
              .statusCode(HttpStatus.CREATED.value())
              .message(ResponseMessages.SUCCESS_SAVE_DATA)
              .data(orderResponse)
              .build();
      return ResponseEntity .status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path = Routes.PATH_VAR_ID)
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
            @RequestParam(name = "orderDate", required = false) @JsonFormat(pattern = "yyyy-MM-dd") String orderDate,
            @RequestParam(name = "totalAmount", required = false) Double totalAmount
    ){


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
                .data(ordersList)
                .build();
        return ResponseEntity.ok(response);

}






    }

