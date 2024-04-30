package com.mfscreener.mfapp.usertransactiondetails;

import com.mfscreener.mfapp.userschemedetails.UserSchemeDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "UserTransactionDetailses")
@EntityListeners(AuditingEntityListener.class)
public class UserTransactionDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
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
    private String type;

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

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(final LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public Double getUnits() {
        return units;
    }

    public void setUnits(final Double units) {
        this.units = units;
    }

    public Double getNav() {
        return nav;
    }

    public void setNav(final Double nav) {
        this.nav = nav;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(final Double balance) {
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getDividendRate() {
        return dividendRate;
    }

    public void setDividendRate(final String dividendRate) {
        this.dividendRate = dividendRate;
    }

    public UserSchemeDetails getUserSchemeDetails() {
        return userSchemeDetails;
    }

    public void setUserSchemeDetails(final UserSchemeDetails userSchemeDetails) {
        this.userSchemeDetails = userSchemeDetails;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
