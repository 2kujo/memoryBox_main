package com.memorybox.domain.cashbox.service;

import com.memorybox.domain.cashbox.entity.CashBox;
import com.memorybox.domain.cashbox.repository.CashBoxRepository;
import com.memorybox.dto.request.CashBoxCreateRequestDto;
import com.memorybox.dto.response.CoreBankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CashBoxWriteService {
    private final CashBoxRepository cashBoxRepository;
    private static String defaultThumbnailImageUrl = "/";
    @Transactional
    public void createCashBox(long userId, CashBoxCreateRequestDto cashBoxCreateRequestDto, CoreBankResponseDto coreBankResponseDto) {
        CashBox cashBox = CashBox.builder()
                .userId(userId)
                .name(cashBoxCreateRequestDto.name())
                .description(cashBoxCreateRequestDto.description())
                .productName(cashBoxCreateRequestDto.productName())
                .thumbnail(defaultThumbnailImageUrl)
                .coreBankId(coreBankResponseDto.coreBankId())
                .accountNum(coreBankResponseDto.accountNum())
                .balance(coreBankResponseDto.balance())
                .productName(coreBankResponseDto.productName())
                .startDate(coreBankResponseDto.startDate())
                .maturityDate(coreBankResponseDto.maturityDate())
                .maturityEnabled(false)
                .build();
        // 만기 여부 maturityEnabled
        cashBoxRepository.save(cashBox);
    }

    @Transactional
    public void updateCashBox(long cashBoxId, CashBox) {

    }
}
