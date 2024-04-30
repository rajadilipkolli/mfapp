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
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(
        name = "user_scheme_details",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "uc_userschemedetailsentity",
                    columnNames = {"isin", "user_folio_details_id"})
        })
@EntityListeners(AuditingEntityListener.class)
public class UserSchemeDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    private List<UserTransactionDetails> userTransactionDetails = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public UserSchemeDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public String getScheme() {
        return scheme;
    }

    public UserSchemeDetails setScheme(String scheme) {
        this.scheme = scheme;
        return this;
    }

    public String getIsin() {
        return isin;
    }

    public UserSchemeDetails setIsin(String isin) {
        this.isin = isin;
        return this;
    }

    public String getAdvisor() {
        return advisor;
    }

    public UserSchemeDetails setAdvisor(String advisor) {
        this.advisor = advisor;
        return this;
    }

    public String getRta() {
        return rta;
    }

    public UserSchemeDetails setRta(String rta) {
        this.rta = rta;
        return this;
    }

    public String getRtaCode() {
        return rtaCode;
    }

    public UserSchemeDetails setRtaCode(String rtaCode) {
        this.rtaCode = rtaCode;
        return this;
    }

    public String getType() {
        return type;
    }

    public UserSchemeDetails setType(String type) {
        this.type = type;
        return this;
    }

    public Long getAmfi() {
        return amfi;
    }

    public UserSchemeDetails setAmfi(Long amfi) {
        this.amfi = amfi;
        return this;
    }

    public String getOpen() {
        return open;
    }

    public UserSchemeDetails setOpen(String open) {
        this.open = open;
        return this;
    }

    public String getClose() {
        return close;
    }

    public UserSchemeDetails setClose(String close) {
        this.close = close;
        return this;
    }

    public String getCloseCalculated() {
        return closeCalculated;
    }

    public UserSchemeDetails setCloseCalculated(String closeCalculated) {
        this.closeCalculated = closeCalculated;
        return this;
    }

    public UserFolioDetails getUserFolioDetails() {
        return userFolioDetails;
    }

    public UserSchemeDetails setUserFolioDetails(UserFolioDetails userFolioDetails) {
        this.userFolioDetails = userFolioDetails;
        return this;
    }

    public List<UserTransactionDetails> getUserTransactionDetails() {
        return userTransactionDetails;
    }

    public UserSchemeDetails setUserTransactionDetails(List<UserTransactionDetails> userTransactionDetails) {
        this.userTransactionDetails = userTransactionDetails;
        return this;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public UserSchemeDetails setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public UserSchemeDetails setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public UserSchemeDetails addUserTransactionDetails(UserTransactionDetails userTransactionDetails) {
        this.userTransactionDetails.add(userTransactionDetails);
        userTransactionDetails.setUserSchemeDetails(this);
        return this;
    }
}
