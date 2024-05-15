package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.request.ReportRequest;
import com.gacortech.eprocurement.dto.response.CommonResponse;
import com.gacortech.eprocurement.dto.response.ReportResponse;

import java.util.List;

public interface ReportService {
    CommonResponse<List<ReportResponse>> getAll(ReportRequest request);
}
