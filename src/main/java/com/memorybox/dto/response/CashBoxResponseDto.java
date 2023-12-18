package com.memorybox.dto.response;

import java.time.LocalDate;

public record CashBoxResponseDto(
        int cashBoxId,
        String name,
        String description,
        int balance,
        LocalDate startDate,
        LocalDate maturityDate
) {
}
