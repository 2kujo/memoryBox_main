package com.memorybox.dto.request;

public record CashBoxCreateRequestDto(
        String name,
        String description,
        String productName
) {
}
