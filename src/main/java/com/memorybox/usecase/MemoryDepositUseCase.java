package com.memorybox.usecase;

import com.memorybox.domain.cashbox.service.CashBoxWriteService;
import com.memorybox.domain.coreBank.service.CoreBankAPIService;
import com.memorybox.domain.memory.service.ImageService;
import com.memorybox.domain.memory.service.MemoryService;
import com.memorybox.dto.request.MemoryCreateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemoryDepositUseCase {

    private final ImageService imageService;
    private final MemoryService memoryService;
    private final CoreBankAPIService coreBankAPIService;
    private final CashBoxWriteService cashBoxWriteService;

    @Transactional
    public void createMemoryAndDeposit(long cashBoxId, MemoryCreateRequestDto requestDto) {
        List<MultipartFile> imageFiles = requestDto.imageFiles();
        List<String> imageNames = imageService.saveImages(imageFiles);

        memoryService.createMemory(cashBoxId, requestDto, imageNames);
        coreBankAPIService.depositMoney(cashBoxId, requestDto.depositAmount());
        cashBoxWriteService.updateBalance(cashBoxId, requestDto.depositAmount());
    }

}
