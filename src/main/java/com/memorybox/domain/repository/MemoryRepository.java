package com.memorybox.domain.repository;


import com.memorybox.domain.entity.Memory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoryRepository extends JpaRepository<Memory, Long> {

    List<Memory> findAllByCashBoxId(long cashBoxId);
}
