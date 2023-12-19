package com.memorybox.dto.response;

import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

public record CoreBankResponseDto(
        long coreBankId,
        String accountNum,
        int balance,
        String productName,
        LocalDate startDate,
        Boolean maturityEnabled,
        LocalDate maturityDate

) {
}
