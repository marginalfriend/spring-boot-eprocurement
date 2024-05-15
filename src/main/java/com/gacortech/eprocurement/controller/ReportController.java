package com.gacortech.eprocurement.controller;

import com.gacortech.eprocurement.constant.Routes;
import com.gacortech.eprocurement.dto.request.ReportRequest;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.dto.response.ReportResponse;
import com.gacortech.eprocurement.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(Routes.REPORT)
public class ReportController {
    ReportService reportService;
    public ResponseEntity<CommonResponse<List<ReportResponse>>> findAll(
            @RequestParam(name = "date", required = false) String date,
            @RequestParam(name = "month", required = false) String month
    ) {
        ReportRequest req = ReportRequest.builder()
                .date(date)
                .month(month)
                .build();

        CommonResponse<List<ReportResponse>> res = reportService.getAll(req);

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
