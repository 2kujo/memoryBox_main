package com.memorybox.domain.cashbox.service;

import com.memorybox.domain.cashbox.entity.CashBox;
import com.memorybox.domain.cashbox.repository.CashBoxRepository;
import com.memorybox.domain.memory.entity.Memory;
import com.memorybox.dto.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CashBoxReadService {
    private final CashBoxRepository cashBoxRepository;

    public CashBoxListResponseDto getCashBoxList(long userId) {
        List<CashBox> cashBoxList = cashBoxRepository.findAllByUserId(userId);
        List<CashBoxListDto> cashBoxListDtos = cashBoxList.stream()
                .map(CashBoxListDto::new)
                .toList();
        return new CashBoxListResponseDto(cashBoxListDtos);
    }

    public CashBoxResponseDto getCashBox(long cashBoxId) {
        CashBox cashBox = cashBoxRepository.findById(cashBoxId).orElseThrow(RuntimeException::new);
        return new CashBoxResponseDto(cashBox);
    }
}
