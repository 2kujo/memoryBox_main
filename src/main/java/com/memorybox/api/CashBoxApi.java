package com.memorybox.api;

import com.memorybox.domain.cashbox.service.CashBoxReadService;
import com.memorybox.domain.cashbox.service.CashBoxWriteService;
import com.memorybox.dto.request.CashBoxCreateRequestDto;
import com.memorybox.usecase.CreateCashBoxUsecase;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cash-boxes")
@RestController
public class CashBoxApi {
    public static final String MEMORYBOX_USER_ID = "memorybox-user-id";
    private final CashBoxReadService cashBoxReadService;
    private final CreateCashBoxUsecase createCashBoxUsecase;
    private final CashBoxWriteService cashBoxWriteService;

    @GetMapping
    public ResponseEntity<?> getCashBoxList(@CookieValue(MEMORYBOX_USER_ID) Cookie userIdCookie, @RequestParam boolean isFinished) {
        long userId = Long.parseLong(userIdCookie.getValue());
        log.info(" [REQUEST] method = getCashBoxList / userId : {}, isFinished : {}", userId, isFinished);
        return ResponseEntity.ok(cashBoxReadService.getCashBoxList(userId, isFinished));
    }

    @GetMapping("/{cashBoxId}")
    public ResponseEntity<?> getCashBox(@PathVariable long cashBoxId) {
        log.info(" [REQUEST] method = getCashBox / cashBoxId : {}", cashBoxId);
        return ResponseEntity.ok(cashBoxReadService.getCashBox(cashBoxId));
    }
    @PostMapping
    public ResponseEntity<?> createCashBox(@CookieValue(MEMORYBOX_USER_ID) Cookie userIdCookie, @RequestBody CashBoxCreateRequestDto cashBoxCreateRequestDto) {
        long userId = Long.parseLong(userIdCookie.getValue());
        log.info(" [REQUEST] method = createCashBox / userId : {}, cashBoxCreateRequestDto : {}", userId, cashBoxCreateRequestDto);
        createCashBoxUsecase.execute(userId, cashBoxCreateRequestDto);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{cashBoxId}")
    public ResponseEntity<?> updateCashBox(@PathVariable long cashBoxId) {
        log.info(" [REQUEST] method = updateCashBox / cashBoxId : {}", cashBoxId);
        cashBoxWriteService.updateCashBox(cashBoxId);
        return ResponseEntity.ok().build();
    }
}
