package com.memorybox.dto.response;

import java.util.List;

public record CashBoxListResponseDto(
        List<CashBoxListDto> cashBoxList
) {
}
