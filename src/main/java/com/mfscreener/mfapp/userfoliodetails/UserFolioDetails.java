package com.mfscreener.mfapp.userfoliodetails;

import com.mfscreener.mfapp.usercasdetails.UserCASDetails;
import com.mfscreener.mfapp.userschemedetails.UserSchemeDetails;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(
        name = "user_folio_details",
        indexes = {@Index(name = "user_details_idx_pan_id", columnList = "id, pan")})
@EntityListeners(AuditingEntityListener.class)
public class UserFolioDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String folio;

    @Column(nullable = false)
    private String amc;

    @Column(length = 10, nullable = false)
    private String pan;

    @Column(length = 10)
    private String kyc;

    @Column(length = 10)
    private String panKyc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_cas_details_id", nullable = false)
    private UserCASDetails userCASDetails;

    @OneToMany(mappedBy = "userFolioDetails")
    private List<UserSchemeDetails> userSchemeDetails = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public UserFolioDetails setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFolio() {
        return folio;
    }

    public UserFolioDetails setFolio(String folio) {
        this.folio = folio;
        return this;
    }

    public String getAmc() {
        return amc;
    }

    public UserFolioDetails setAmc(String amc) {
        this.amc = amc;
        return this;
    }

    public String getPan() {
        return pan;
    }

    public UserFolioDetails setPan(String pan) {
        this.pan = pan;
        return this;
    }

    public String getKyc() {
        return kyc;
    }

    public UserFolioDetails setKyc(String kyc) {
        this.kyc = kyc;
        return this;
    }

    public String getPanKyc() {
        return panKyc;
    }

    public UserFolioDetails setPanKyc(String panKyc) {
        this.panKyc = panKyc;
        return this;
    }

    public UserCASDetails getUserCASDetails() {
        return userCASDetails;
    }

    public UserFolioDetails setUserCASDetails(UserCASDetails userCASDetails) {
        this.userCASDetails = userCASDetails;
        return this;
    }

    public List<UserSchemeDetails> getUserSchemeDetails() {
        return userSchemeDetails;
    }

    public UserFolioDetails setUserSchemeDetails(List<UserSchemeDetails> userSchemeDetails) {
        this.userSchemeDetails = userSchemeDetails;
        return this;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public UserFolioDetails setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public UserFolioDetails setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public void addUserSchemeDetails(UserSchemeDetails userSchemeDetails) {
        this.userSchemeDetails.add(userSchemeDetails);
        userSchemeDetails.setUserFolioDetails(this);
    }
}
