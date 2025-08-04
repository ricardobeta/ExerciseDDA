package com.rb.bp.accounts.infrastructure.adapter.in.web;

import com.rb.bp.accounts.application.port.in.GetReportUseCase;
import com.rb.bp.accounts.domain.model.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final GetReportUseCase getReportUseCase;

    @GetMapping
    public ResponseEntity<List<Report>> getReportByCustomerAndDateRange(
            @RequestParam("customerId") Long customerId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Report> reports = getReportUseCase.getReportByDateRangeAndUser(startDate, endDate, customerId);
        return ResponseEntity.ok(reports);
    }
}
