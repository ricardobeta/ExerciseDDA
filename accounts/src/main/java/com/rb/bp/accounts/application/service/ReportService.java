package com.rb.bp.accounts.application.service;

import com.rb.bp.accounts.application.port.in.GetReportUseCase;
import com.rb.bp.accounts.application.port.out.TransactionRepository;
import com.rb.bp.accounts.domain.model.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService implements GetReportUseCase {

    private final TransactionRepository transactionRepository;


    @Override
    public List<Report> getReportByDateRangeAndUser(LocalDate startDate, LocalDate endDate, Long customerId) {
        return transactionRepository.getReport(startDate,endDate,customerId);
    }
}
