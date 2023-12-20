package com.memorybox.domain.cashbox.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class CashBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private long userId;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String thumbnail;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;

    // CoreBank 에서 가져온 계좌 정보
    @Column
    private long coreBankId;

    @Column
    private String accountNum;

    @Column
    private int balance;

    @Column
    private String productName;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate maturityDate;

    @Column
    private boolean maturityEnabled;

    @Builder
    public CashBox(long userId, String name, String description, String thumbnail, LocalDateTime createdAt, long coreBankId, String accountNum, int balance, String productName, LocalDate startDate, LocalDate maturityDate, boolean maturityEnabled) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.accountNum = accountNum;
        this.balance = balance;
        this.productName = productName;
        this.coreBankId = coreBankId;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.maturityEnabled = maturityEnabled;
        this.maturityDate = maturityDate;
    }
}
