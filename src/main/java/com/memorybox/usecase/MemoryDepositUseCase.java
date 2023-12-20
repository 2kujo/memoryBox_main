package com.memorybox.usecase;

import com.memorybox.domain.cashbox.service.CashBoxWriteService;
import com.memorybox.domain.coreBank.service.CoreBankAPIService;
import com.memorybox.domain.memory.service.ImageService;
import com.memorybox.domain.memory.service.MemoryService;
import com.memorybox.dto.request.MemoryCreateRequestDto;
import com.memorybox.dto.response.MemoryCreateResponseDto;
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
    public MemoryCreateResponseDto createMemoryAndDeposit(long cashBoxId, MemoryCreateRequestDto requestDto) {
        log.info(" >>> CreateMemoryAndDeposit Start!");
        List<MultipartFile> imageFiles = requestDto.imageFiles();
        List<String> imageNames = imageService.saveImages(imageFiles);
        log.info("  Finish Image Save!");
        MemoryCreateResponseDto createResponseDto = memoryService.createMemory(cashBoxId, requestDto, imageNames);
        log.info("  Finish Memory Save!");
        coreBankAPIService.depositMoney(cashBoxId, requestDto.getDepositAmount());
        log.info("  Finish Core Api Call!");
        cashBoxWriteService.updateBalance(cashBoxId, requestDto.getDepositAmount());
        log.info("  Finish CashBox Save!");
        return createResponseDto;
    }

}
