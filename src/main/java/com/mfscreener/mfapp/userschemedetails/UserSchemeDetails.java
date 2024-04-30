package com.mfscreener.mfapp.userschemedetails;

import com.mfscreener.mfapp.userfoliodetails.UserFolioDetails;
import com.mfscreener.mfapp.usertransactiondetails.UserTransactionDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "UserSchemeDetailses")
@EntityListeners(AuditingEntityListener.class)
public class UserSchemeDetails {

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
    private String scheme;

    @Column(length = 25)
    private String isin;

    @Column
    private String advisor;

    @Column
    private String rta;

    @Column
    private String rtaCode;

    @Column
    private String type;

    @Column
    private Long amfi;

    @Column
    private String open;

    @Column
    private String close;

    @Column
    private String closeCalculated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_folio_details_id")
    private UserFolioDetails userFolioDetails;

    @OneToMany(mappedBy = "userSchemeDetails")
    private Set<UserTransactionDetails> userTransactionDetails;

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

    public String getScheme() {
        return scheme;
    }

    public void setScheme(final String scheme) {
        this.scheme = scheme;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(final String isin) {
        this.isin = isin;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(final String advisor) {
        this.advisor = advisor;
    }

    public String getRta() {
        return rta;
    }

    public void setRta(final String rta) {
        this.rta = rta;
    }

    public String getRtaCode() {
        return rtaCode;
    }

    public void setRtaCode(final String rtaCode) {
        this.rtaCode = rtaCode;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Long getAmfi() {
        return amfi;
    }

    public void setAmfi(final Long amfi) {
        this.amfi = amfi;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(final String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(final String close) {
        this.close = close;
    }

    public String getCloseCalculated() {
        return closeCalculated;
    }

    public void setCloseCalculated(final String closeCalculated) {
        this.closeCalculated = closeCalculated;
    }

    public UserFolioDetails getUserFolioDetails() {
        return userFolioDetails;
    }

    public void setUserFolioDetails(final UserFolioDetails userFolioDetails) {
        this.userFolioDetails = userFolioDetails;
    }

    public Set<UserTransactionDetails> getUserTransactionDetails() {
        return userTransactionDetails;
    }

    public void setUserTransactionDetails(
            final Set<UserTransactionDetails> userTransactionDetails) {
        this.userTransactionDetails = userTransactionDetails;
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
