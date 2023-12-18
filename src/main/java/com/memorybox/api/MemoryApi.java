package com.memorybox.api;

import com.memorybox.domain.service.MemoryService;
import com.memorybox.dto.request.MemoryCreateRequestDto;
import com.memorybox.dto.response.MemoryListResponseDto;
import com.memorybox.dto.response.MemoryResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cash-boxes/{cashBoxId}")
@RestController
public class MemoryApi {

    private final MemoryService memoryService;

    @GetMapping("/memories")
    public ResponseEntity<?> getMemoryList(@CookieValue("memorybox-user-id") long userId,
                                         @PathVariable long cashBoxId) {
        MemoryListResponseDto responseDto = memoryService.getMemoryList(cashBoxId);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/memories/{memoryId}")
    public ResponseEntity<?> getMemories(@CookieValue("memorybox-user-id") long userId,
                                         @PathVariable long memoryId) {
        MemoryResponseDto dto = memoryService.getMemory(memoryId);

        return ResponseEntity.ok(dto);
    }

    @PostMapping("/memories")
    public ResponseEntity<?> createMemory(@PathVariable long cashBoxId, @RequestBody MemoryCreateRequestDto requestDto) {
        //TODO 이미지 저장 로직 추가
        memoryService.createMemory(cashBoxId, requestDto);

        return ResponseEntity.ok().build();
    }
}
