package com.memorybox.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.memorybox.domain.cashbox.entity.CashBox;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CashBoxResponseDto(
        long cashBoxId,
        String name,
        String accountNum,
        String productName,
        int balance,
        boolean finished
) {
    public CashBoxResponseDto(CashBox entity) {
        this(entity.getId(), entity.getName(), entity.getAccountNum(), entity.getProductName(), entity.getBalance(), entity.isMaturityEnabled());
    }
}
