package com.memorybox.domain.cashbox.repository;

import com.memorybox.domain.cashbox.entity.CashBox;
import com.memorybox.domain.memory.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CashBoxRepository extends JpaRepository<CashBox, Long> {
    List<CashBox> findAllByUserId(long userId);
}
