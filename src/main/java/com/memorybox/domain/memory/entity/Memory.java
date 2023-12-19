package com.memorybox.domain.memory.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
public class Memory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long cashBoxId;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private int depositAmount;

    @Column
    private List<String> images;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public Memory(long cashBoxId, String title, String content, int depositAmount, List<String> images) {
        this.cashBoxId = cashBoxId;
        this.title = title;
        this.content = content;
        this.depositAmount = depositAmount;
        this.images = images;
    }
}
