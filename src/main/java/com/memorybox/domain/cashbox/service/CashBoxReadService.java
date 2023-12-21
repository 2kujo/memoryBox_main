package com.memorybox.domain.cashbox.service;

import com.memorybox.domain.cashbox.entity.CashBox;
import com.memorybox.domain.cashbox.repository.CashBoxRepository;
import com.memorybox.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CashBoxReadService {
    private final CashBoxRepository cashBoxRepository;

    public CashBoxListResponseDto getCashBoxList(long userId, boolean isFinished) {
        List<CashBox> cashBoxList = cashBoxRepository.findAllByUserId(userId);
        log.info("cashBoxList 응답 개수 = {}", cashBoxList.size());
        List<CashBoxListDto> cashBoxListDtos = cashBoxList.stream()
                .filter(c -> c.isMaturityEnabled() == isFinished)
                .map(CashBoxListDto::new)
                .collect(Collectors.toList());
        return new CashBoxListResponseDto(cashBoxListDtos);
    }

    public CashBoxResponseDto getCashBox(long cashBoxId) {
        CashBox cashBox = cashBoxRepository.findById(cashBoxId)
                .orElseThrow(RuntimeException::new);
        return new CashBoxResponseDto(cashBox);
    }
}
