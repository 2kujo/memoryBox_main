package com.memorybox.api;

import com.memorybox.domain.cashbox.service.CashBoxReadService;
import com.memorybox.domain.cashbox.service.CashBoxWriteService;
import com.memorybox.dto.request.CashBoxCreateRequestDto;
import com.memorybox.dto.request.CashBoxGetRequestDto;
import com.memorybox.usecase.CreateCashBoxUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cash-boxes")
@RestController
public class CashBoxApi {
    private final CashBoxReadService cashBoxReadService;
    private final CreateCashBoxUsecase createCashBoxUsecase;

    @GetMapping("")
    public ResponseEntity<?> getCashBoxList(@CookieValue("memorybox-user-id") long userId, @RequestParam Boolean maturityEnabled) {
        return ResponseEntity.ok(cashBoxReadService.getCashBoxList(userId));
    }

    @GetMapping("/{cashBoxId}")
    public ResponseEntity<?> getCashBox(@CookieValue("memorybox-user-id") long userId, @PathVariable long cashBoxId) {
        return ResponseEntity.ok(cashBoxReadService.getCashBox(userId));
    }
    @PostMapping("cash-boxes")
    public ResponseEntity<?> createCashBox(@CookieValue("memorybox-user-id") long userId, @RequestBody CashBoxCreateRequestDto cashBoxCreateRequestDto) {
        createCashBoxUsecase.execute(userId, cashBoxCreateRequestDto);
        return ResponseEntity.ok().build();
    }
}
