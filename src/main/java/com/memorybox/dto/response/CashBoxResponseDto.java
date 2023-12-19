package com.memorybox.dto.response;

import com.memorybox.domain.cashbox.entity.CashBox;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CashBoxResponseDto(
        long cashBoxId,
        String name,
        String description,
        int balance,
        LocalDate startDate,
        LocalDate maturityDate
) {
    public CashBoxResponseDto(CashBox entity) {
        this(entity.getId(), entity.getName(), entity.getDescription(), entity.getBalance(), entity.getStartDate(), entity.getMaturityDate());
    }
}
