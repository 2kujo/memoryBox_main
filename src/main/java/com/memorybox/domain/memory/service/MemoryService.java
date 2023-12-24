package com.memorybox.domain.memory.service;

import com.memorybox.domain.memory.entity.Memory;
import com.memorybox.domain.memory.repository.MemoryRepository;
import com.memorybox.dto.request.MemoryCreateRequestDto;
import com.memorybox.dto.response.MemoryCreateResponseDto;
import com.memorybox.dto.response.MemoryListDto;
import com.memorybox.dto.response.MemoryListResponseDto;
import com.memorybox.dto.response.MemoryResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemoryService {

    private final MemoryRepository memoryRepository;

    @Transactional(readOnly = true)
    public MemoryListResponseDto getMemoryList(long cashBoxId) {
        List<Memory> memoryList = memoryRepository.findAllByCashBoxIdOrderByCreatedAtDesc(cashBoxId);

        List<MemoryListDto> memoryListDtos = memoryList.stream()
                .map(MemoryListDto::new)
                .collect(Collectors.toList());
        return new MemoryListResponseDto(memoryListDtos);
    }

    @Transactional(readOnly = true)
    public MemoryResponseDto getMemory(long memoryId) {
        Memory memory = memoryRepository.findById(memoryId)
                .orElseThrow(RuntimeException::new);
        return new MemoryResponseDto(memory);
    }

    @Transactional
    public MemoryCreateResponseDto createMemory(long cashBoxId, MemoryCreateRequestDto requestDto, List<String> imageNames) {
        log.info(" Service: createMemory / cashBoxId : {}, MemoryCreateRequestDto : {}, imageNames : {}",
                cashBoxId, requestDto, imageNames);
        Memory createMemory = Memory.builder()
                .cashBoxId(cashBoxId)
                .title(requestDto.title())
                .content(requestDto.content())
                .depositAmount(requestDto.getDepositAmount())
                .images(imageNames)
                .build();
        Memory memory = memoryRepository.save(createMemory);
        return new MemoryCreateResponseDto(memory.getId());
    }
}
