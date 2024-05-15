package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.ResponseMessages;
import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.request.ReportRequest;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.dto.response.ReportResponse;
import com.gacortech.eprocurement.service.ReportService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(Routes.REPORT)
public class ReportController {
    private final ReportService reportService;

    @GetMapping
    public ResponseEntity<CommonResponse<List<ReportResponse>>> findAll(
            @RequestParam(name = "date",    required = false) String    date,
            @RequestParam(name = "month",   required = false) String    month,
            @RequestParam(name = "page",    required = false) Integer   page,
            @RequestParam(name = "size",    required = false) Integer   size
    ) {
        ReportRequest req = ReportRequest.builder()
                .date(date)
                .month(month)
                .page(page)
                .size(size)
                .build();

        CommonResponse<List<ReportResponse>> res = reportService.getAll(req);
        res.setStatusCode(HttpStatus.OK.value());
        res.setMessage(ResponseMessages.SUCCESS_GET_DATA);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
