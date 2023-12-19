package com.memorybox.domain.cashbox.service;

import com.memorybox.domain.cashbox.entity.CashBox;
import com.memorybox.domain.cashbox.repository.CashBoxRepository;
import com.memorybox.dto.request.CashBoxCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CashBoxWriteService {
    private final CashBoxRepository cashBoxRepository;
    private static String defaultThumbnailImageUrl = "/";
    public void createCashBox(long userId, CashBoxCreateRequestDto cashBoxCreateRequestDto) {
        CashBox cashBox = CashBox.builder()
                .userId(userId)
                .name(cashBoxCreateRequestDto.name())
                .description(cashBoxCreateRequestDto.description())
                .productName(cashBoxCreateRequestDto.productName())
                .thumbnail(defaultThumbnailImageUrl)
                .build();
        cashBoxRepository.save()
    }
}
