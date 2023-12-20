package com.memorybox.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.memorybox.domain.cashbox.entity.CashBox;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CashBoxListDto(
        long cashBoxId,
        String name,
        int balance,
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy.MM.dd")
        LocalDate maturityDate,
        String thumbnail
) {
    public CashBoxListDto(CashBox cashBox) {
        this(cashBox.getId(), cashBox.getName(), cashBox.getBalance(),cashBox.getStartDate(),cashBox.getMaturityDate(),cashBox.getThumbnail());
    }
}
