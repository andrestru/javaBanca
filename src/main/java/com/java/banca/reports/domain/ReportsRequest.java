package com.java.banca.reports.domain;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReportsRequest {
    
    private LocalDate startDate;
    private LocalDate endDate;
    private Long clientId;
    
}