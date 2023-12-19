package com.memorybox.usecase;

import com.memorybox.domain.cashbox.service.CashBoxReadService;
import com.memorybox.domain.cashbox.service.CashBoxWriteService;
import com.memorybox.domain.coreBank.service.CoreBankAPIService;
import com.memorybox.dto.request.CashBoxCreateRequestDto;
import com.memorybox.dto.response.CoreBankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCashBoxUsecase {
    /*
        cashBox와 memory의 오케스트레이션 -> 흐름만을 제어해주는 서비스
     */
    private final CashBoxWriteService cashBoxWriteService;
    private final CoreBankAPIService coreBankAPIService;

    public void execute(Long userId, CashBoxCreateRequestDto cashBoxCreateRequestDto) {
        /*
            외부 api를 통해 받아온 CoreBankResponseDto를 cashBoxWriteService에 넘겨준다.
         */
        CoreBankResponseDto coreBankResponseDto = coreBankAPIService.fetchCashBoxDataFromCoreBankAPI(userId, cashBoxCreateRequestDto.productName());
        cashBoxWriteService.createCashBox(userId, cashBoxCreateRequestDto, coreBankResponseDto);
    }
}
