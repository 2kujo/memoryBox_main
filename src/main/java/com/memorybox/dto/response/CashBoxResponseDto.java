package com.memorybox.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.memorybox.domain.cashbox.entity.CashBox;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CashBoxResponseDto(
        long cashBoxId,
        String name,
        String description,
        int balance,
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate maturityDate
) {
    public CashBoxResponseDto(CashBox entity) {
        this(entity.getId(), entity.getName(), entity.getDescription(), entity.getBalance(), entity.getStartDate(), entity.getMaturityDate());
    }
}
