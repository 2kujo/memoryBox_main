package com.memorybox.api;

import com.memorybox.dto.request.CashBoxGetRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cash-boxes")
@RestController
public class CashBoxApi {
    @GetMapping("")
    public ResponseEntity<?> getCashBoxList(@CookieValue("memorybox-user-id") long userId, @RequestBody CashBoxGetRequestDto requestDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{cashBoxId}")
    public ResponseEntity<?> getCashBox(@CookieValue("memorybox-user-id") long userId, @PathVariable long cashBoxId) {
        return ResponseEntity.ok().build();
    }
    @PostMapping("cash-boxes")
    public ResponseEntity<?> createCashBox() {
        return ResponseEntity.ok().build();
    }
}
