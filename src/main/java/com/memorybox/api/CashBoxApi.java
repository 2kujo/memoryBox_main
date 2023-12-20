package com.memorybox.api;

import com.memorybox.domain.cashbox.service.CashBoxReadService;
import com.memorybox.domain.cashbox.service.CashBoxWriteService;
import com.memorybox.dto.request.CashBoxCreateRequestDto;
import com.memorybox.usecase.CreateCashBoxUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/cash-boxes")
@RestController
public class CashBoxApi {
    public static final String MEMORYBOX_USER_ID = "memorybox-user-id";
    private final CashBoxReadService cashBoxReadService;
    private final CreateCashBoxUsecase createCashBoxUsecase;
    private final CashBoxWriteService cashBoxWriteService;

    @GetMapping("")
    public ResponseEntity<?> getCashBoxList(@CookieValue(MEMORYBOX_USER_ID) long userId,
            @RequestParam boolean requestIsFinished) {
        return ResponseEntity.ok(cashBoxReadService.getCashBoxList(userId, requestIsFinished));
    }

    @GetMapping("/{cashBoxId}")
    public ResponseEntity<?> getCashBox(@CookieValue(MEMORYBOX_USER_ID) long userId, @PathVariable long cashBoxId) {
        return ResponseEntity.ok(cashBoxReadService.getCashBox(userId));
    }
    @PostMapping("")
    public ResponseEntity<?> createCashBox(@CookieValue(MEMORYBOX_USER_ID) long userId, @RequestBody CashBoxCreateRequestDto cashBoxCreateRequestDto) {
        createCashBoxUsecase.execute(userId, cashBoxCreateRequestDto);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{cashBoxId}")
    public ResponseEntity<?> updateCashBox(@PathVariable long cashBoxId) {
        cashBoxWriteService.updateCashBox(cashBoxId);
        return ResponseEntity.ok().build();
    }
}
