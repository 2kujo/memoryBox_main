package com.memorybox.domain.repository;

import com.memorybox.domain.entity.CashBox;
import com.memorybox.domain.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashBoxRepository extends JpaRepository<CashBox, Long> {

}
