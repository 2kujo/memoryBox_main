package com.memorybox.dto.response;

import com.memorybox.domain.cashbox.entity.CashBox;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
public record CashBoxListDto(
        Long cashBoxId,
        String name,
        int balance,
        LocalDate startDate,
        LocalDate maturityDate,
        String thumbnail
) {
    public CashBoxListDto(CashBox cashBox) {
        this(cashBox.getId(), cashBox.getName(), cashBox.getBalance(),cashBox.getStartDate(),cashBox.getMaturityDate(),cashBox.getThumbnail());
    }
}
