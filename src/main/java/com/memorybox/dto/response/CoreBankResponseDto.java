package com.memorybox.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record CoreBankResponseDto(
        long coreBankId,
        String accountNum,
        int balance,
        String productName,
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate maturityDate

) {
}
