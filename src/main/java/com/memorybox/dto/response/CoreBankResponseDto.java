package com.memorybox.dto.response;

import java.time.LocalDate;

public record CoreBankResponseDto(
        long coreBankId,
        String accountNum,
        int balance,
        String productName,
        LocalDate startDate,
        LocalDate maturityDate

) {
}
