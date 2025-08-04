package com.rb.bp.accounts.application.port.in;

import com.rb.bp.accounts.domain.model.Report;

import java.time.LocalDate;
import java.util.List;

public interface GetReportUseCase {
    List<Report> getReportByDateRangeAndUser(LocalDate startDate, LocalDate endDate, Long customerId);
}
