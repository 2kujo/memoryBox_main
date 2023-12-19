package com.memorybox.domain.service;

import com.memorybox.domain.entity.Memory;
import com.memorybox.domain.repository.MemoryRepository;
import com.memorybox.dto.request.MemoryCreateRequestDto;
import com.memorybox.dto.response.MemoryListDto;
import com.memorybox.dto.response.MemoryListResponseDto;
import com.memorybox.dto.response.MemoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MemoryService {

    private final ImageService imageService;

    private final MemoryRepository memoryRepository;

    @Transactional(readOnly = true)
    public MemoryListResponseDto getMemoryList(long cashBoxId) {
        List<Memory> memoryList = memoryRepository.findAllByCashBoxId(cashBoxId);

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
    public void createMemory(long cashBoxId, MemoryCreateRequestDto requestDto) {
        //이미지 저장 로직 -> 이후 비동기 처리 or API 별도 분리
        List<MultipartFile> imageFiles = requestDto.imageFiles();
        List<String> imageNames = imageService.saveImages(imageFiles);

        //입금 기록 저장 로직
        Memory createMemory = Memory.builder()
                .cashBoxId(cashBoxId)
                .title(requestDto.title())
                .content(requestDto.content())
                .depositAmount(requestDto.depositAmount())
                .images(imageNames)
                .build();
        memoryRepository.save(createMemory);

        //TODO 1 cashBox 조회
        //TODO 2 cashBox의 externalId로 입금 External에 요청
        //TODO 3 cashBox의 잔액에 추가
    }
}
