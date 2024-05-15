package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.request.ReportRequest;
import com.gacortech.eprocurement.dto.response.ReportResponse;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface ReportService {
    List<ReportResponse> getAll(ReportRequest request);
}
