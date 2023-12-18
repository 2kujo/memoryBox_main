package com.memorybox.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CashBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String name;

    @Column
    private Long description;

    @Column
    private String thumbnail;

    @Column
    private String accountNum;

    @Column
    int balance;

    @Column
    private String productName;

    @Column
    private int externalId;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate start_date;

    @Column
    private Boolean maturityEnabled;

    @Column
    private LocalDate maturityDate;

    @Builder
    public CashBox(Long userId, String name, Long description, String thumbnail, String accountNum, int balance, String productName, int externalId, LocalDate createdAt, LocalDate start_date, Boolean maturityEnabled, LocalDate maturityDate) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.accountNum = accountNum;
        this.balance = balance;
        this.productName = productName;
        this.externalId = externalId;
        this.createdAt = createdAt;
        this.start_date = start_date;
        this.maturityEnabled = maturityEnabled;
        this.maturityDate = maturityDate;
    }
}
