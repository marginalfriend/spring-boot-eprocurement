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
            @RequestParam(name = "Date", required = false) @JsonFormat(pattern = "yyyy-MM-dd") String Date,
            @RequestParam(name = "maxAmount", required = false) Integer maxAmount,
            @RequestParam(name = "minAmount", required = false) Integer minAmount,
            @RequestParam(name = "startFrom", required = false) String startFrom,
            @RequestParam(name = "endTo", required = false) String endTo,
            @RequestParam(name = "sortBy", required = false) String sortBy,
            @RequestParam(name = "direction", required = false) String direction,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size
    ){


        SearchOrderRequest request = SearchOrderRequest.builder()

                .Date(Date)
                .maxAmount(maxAmount)
                .minAmount(minAmount)
                .startFrom(startFrom)
                .endTo(endTo)
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

