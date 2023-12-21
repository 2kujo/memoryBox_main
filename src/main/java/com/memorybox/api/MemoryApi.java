package com.memorybox.api;

import com.memorybox.domain.memory.service.MemoryService;
import com.memorybox.dto.request.MemoryCreateRequestDto;
import com.memorybox.dto.response.MemoryCreateResponseDto;
import com.memorybox.dto.response.MemoryListResponseDto;
import com.memorybox.dto.response.MemoryResponseDto;
import com.memorybox.usecase.MemoryDepositUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cash-boxes/{cashBoxId}")
@RestController
public class MemoryApi {

    private final MemoryService memoryService;

    private final MemoryDepositUseCase memoryDepositUseCase;

    @GetMapping("/memories")
    public ResponseEntity<?> getMemoryList(@PathVariable long cashBoxId) {
        log.info(" [REQUEST] method = getMemoryList / cashBoxId : {} ", cashBoxId);
        MemoryListResponseDto responseDto = memoryService.getMemoryList(cashBoxId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/memories/{memoryId}")
    public ResponseEntity<?> getMemory(@PathVariable long cashBoxId, @PathVariable long memoryId) {
        log.info(" [REQUEST] method = getMemory / memoryId : {} ", memoryId);
        MemoryResponseDto dto = memoryService.getMemory(memoryId);
        log.info(" [RESPONSE] method = getMemory / MemoryResponseDto : {} ", dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/memories")
    public ResponseEntity<?> createMemory(@PathVariable long cashBoxId, @ModelAttribute MemoryCreateRequestDto requestDto) {
        log.info(" [REQUEST] method = createMemory / cashBoxId : {}, requestDto : {}", cashBoxId, requestDto);
        MemoryCreateResponseDto responseDto = memoryDepositUseCase.createMemoryAndDeposit(cashBoxId, requestDto);
        log.info(" [RESPONSE] method = createMemory / MemoryCreateResponseDto : {} ", responseDto);
        return ResponseEntity.ok(responseDto);
    }
}
