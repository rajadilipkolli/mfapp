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
@Table(name = "UserFolioDetailses")
@EntityListeners(AuditingEntityListener.class)
public class UserFolioDetails {

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
    private String folio;

    @Column
    private String amc;

    @Column(length = 10)
    private String pan;

    @Column(length = 10)
    private String kyc;

    @Column(length = 10)
    private String panKyc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usercasdetails_id")
    private UserCASDetails userCASDetails;

    @OneToMany(mappedBy = "userFolioDetails")
    private Set<UserSchemeDetails> userSchemeDetails;

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

    public String getFolio() {
        return folio;
    }

    public void setFolio(final String folio) {
        this.folio = folio;
    }

    public String getAmc() {
        return amc;
    }

    public void setAmc(final String amc) {
        this.amc = amc;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(final String pan) {
        this.pan = pan;
    }

    public String getKyc() {
        return kyc;
    }

    public void setKyc(final String kyc) {
        this.kyc = kyc;
    }

    public String getPanKyc() {
        return panKyc;
    }

    public void setPanKyc(final String panKyc) {
        this.panKyc = panKyc;
    }

    public UserCASDetails getUserCASDetails() {
        return userCASDetails;
    }

    public void setUserCASDetails(final UserCASDetails userCASDetails) {
        this.userCASDetails = userCASDetails;
    }

    public Set<UserSchemeDetails> getUserSchemeDetails() {
        return userSchemeDetails;
    }

    public void setUserSchemeDetails(final Set<UserSchemeDetails> userSchemeDetails) {
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
