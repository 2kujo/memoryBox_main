package com.memorybox.dto.request;

public record BalanceUpdateRequestDto(long coreBankId, int depositAmount) {
}