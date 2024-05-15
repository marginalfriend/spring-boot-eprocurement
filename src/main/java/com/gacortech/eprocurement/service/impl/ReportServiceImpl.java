package com.gacortech.eprocurement.service.impl;

import com.gacortech.eprocurement.dto.response.ReportResponse;
import com.gacortech.eprocurement.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public List<ReportResponse> getByDate(LocalDate date) {
        return null;
    }

    @Override
    public List<ReportResponse> getByMonth(Month month) {
        return null;
    }
}
