package com.memorybox.api;

import com.memorybox.common.resolver.CertUserId;
import com.memorybox.domain.cashbox.service.CashBoxReadService;
import com.memorybox.domain.cashbox.service.CashBoxWriteService;
import com.memorybox.dto.request.CashBoxCreateRequestDto;
import com.memorybox.usecase.CreateCashBoxUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/cash-boxes")
@RestController
public class CashBoxApi {
    private final CashBoxReadService cashBoxReadService;
    private final CreateCashBoxUsecase createCashBoxUsecase;
    private final CashBoxWriteService cashBoxWriteService;

    @GetMapping
    public ResponseEntity<?> getCashBoxList(@CertUserId long userId, @RequestParam boolean isFinished) {
        log.info(" [REQUEST] method = getCashBoxList / userId : {}, isFinished : {}", userId, isFinished);
        return ResponseEntity.ok(cashBoxReadService.getCashBoxList(userId, isFinished));
    }

    @GetMapping("/{cashBoxId}")
    public ResponseEntity<?> getCashBox(@PathVariable long cashBoxId) {
        log.info(" [REQUEST] method = getCashBox / cashBoxId : {}", cashBoxId);
        return ResponseEntity.ok(cashBoxReadService.getCashBox(cashBoxId));
    }
    @PostMapping
    public ResponseEntity<?> createCashBox(@CertUserId long userId, @RequestBody CashBoxCreateRequestDto cashBoxCreateRequestDto) {
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
