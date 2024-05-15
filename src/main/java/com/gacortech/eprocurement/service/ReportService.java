package com.gacortech.eprocurement.service;

import com.gacortech.eprocurement.dto.response.ReportResponse;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface ReportService {
    public List<ReportResponse> getByDate(LocalDate date);
    public List<ReportResponse> getByMonth(Month month);
}
