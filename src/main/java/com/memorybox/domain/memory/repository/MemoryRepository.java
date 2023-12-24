package com.memorybox.domain.memory.repository;


import com.memorybox.domain.memory.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    List<Memory> findAllByCashBoxIdOrderByCreatedAtDesc(long cashBoxId);
}
