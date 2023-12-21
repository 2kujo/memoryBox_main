package com.memorybox.usecase;

import com.memorybox.domain.cashbox.service.CashBoxWriteService;
import com.memorybox.domain.coreBank.service.CoreBankAPIService;
import com.memorybox.dto.request.CashBoxCreateRequestDto;
import com.memorybox.dto.response.CoreBankResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateCashBoxUsecase {
    /*
        cashBox와 memory의 오케스트레이션 -> 흐름만을 제어해주는 서비스
     */
    private final CashBoxWriteService cashBoxWriteService;
    private final CoreBankAPIService coreBankAPIService;
    @Transactional
    public void execute(long userId, CashBoxCreateRequestDto cashBoxCreateRequestDto) {
        /*
            외부 api를 통해 받아온 CoreBankResponseDto를 cashBoxWriteService에 넘겨준다.
         */
        CoreBankResponseDto coreBankResponseDto = coreBankAPIService.fetchCashBoxDataFromCoreBankAPI(userId, cashBoxCreateRequestDto.productName());
        log.info("CoreBank Response Dto = {}", coreBankResponseDto);
        cashBoxWriteService.createCashBox(userId, cashBoxCreateRequestDto, coreBankResponseDto);
    }
}
