package com.mfscreener.mfapp.usertransactiondetails;

import com.mfscreener.mfapp.userschemedetails.UserSchemeDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(
        name = "user_transaction_details",
        indexes = {@Index(name = "user_details_idx_type_transaction_date", columnList = "transaction_date, type")})
@EntityListeners(AuditingEntityListener.class)
public class UserTransactionDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    private LocalDate transactionDate;

    @Column
    private String description;

    @Column
    private Double amount;

    @Column
    private Double units;

    @Column
    private Double nav;

    @Column
    private Double balance;

    @Column
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column
    private String dividendRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_scheme_details_id")
    private UserSchemeDetails userSchemeDetails;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public UserTransactionDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public UserTransactionDetails setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserTransactionDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public UserTransactionDetails setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Double getUnits() {
        return units;
    }

    public UserTransactionDetails setUnits(Double units) {
        this.units = units;
        return this;
    }

    public Double getNav() {
        return nav;
    }

    public UserTransactionDetails setNav(Double nav) {
        this.nav = nav;
        return this;
    }

    public Double getBalance() {
        return balance;
    }

    public UserTransactionDetails setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public TransactionType getType() {
        return type;
    }

    public UserTransactionDetails setType(TransactionType type) {
        this.type = type;
        return this;
    }

    public String getDividendRate() {
        return dividendRate;
    }

    public UserTransactionDetails setDividendRate(String dividendRate) {
        this.dividendRate = dividendRate;
        return this;
    }

    public UserSchemeDetails getUserSchemeDetails() {
        return userSchemeDetails;
    }

    public UserTransactionDetails setUserSchemeDetails(UserSchemeDetails userSchemeDetails) {
        this.userSchemeDetails = userSchemeDetails;
        return this;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public UserTransactionDetails setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public UserTransactionDetails setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }
}
