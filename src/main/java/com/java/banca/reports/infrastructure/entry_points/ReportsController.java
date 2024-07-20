package com.java.banca.reports.infrastructure.entry_points;

import org.springframework.web.bind.annotation.RestController;

import com.java.banca.reports.domain.ReportsRequest;
import com.java.banca.reports.domain.ReportsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.java.banca.reports.domain.usecase.ReportUseCase;

import java.time.LocalDate;

@RestController
public class ReportsController {

    private final ReportUseCase reportUseCase;

    @Autowired
    public ReportsController(ReportUseCase reportUseCase) {
        this.reportUseCase = reportUseCase;
    }

    @GetMapping("/reports")
    public ResponseEntity<ReportsResponse> generateReport(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("clientId") Long clientId) {
        ReportsRequest request = new ReportsRequest();
        request.setStartDate(startDate);
        request.setEndDate(endDate);
        request.setClientId(clientId);

        return ResponseEntity.ok(reportUseCase.generateReport(request));
    }
}
