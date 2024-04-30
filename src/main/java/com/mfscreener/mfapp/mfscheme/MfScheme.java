package com.mfscreener.mfapp.mfscheme;

import com.mfscreener.mfapp.mfschemenav.MFSchemeNav;
import com.mfscreener.mfapp.mfschemetype.MfSchemeType;
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
@Table(name = "MfSchemes")
@EntityListeners(AuditingEntityListener.class)
public class MfScheme {

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
    private Long schemeId;

    @Column(length = 15)
    private String isin;

    @Column(length = 100)
    private String fundHouse;

    @Column
    private String schemeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_scheme_type_id")
    private MfSchemeType mfSchemeType;

    @OneToMany(mappedBy = "mfScheme")
    private Set<MFSchemeNav> mFschemeNavs;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(final Long schemeId) {
        this.schemeId = schemeId;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(final String isin) {
        this.isin = isin;
    }

    public String getFundHouse() {
        return fundHouse;
    }

    public void setFundHouse(final String fundHouse) {
        this.fundHouse = fundHouse;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(final String schemeName) {
        this.schemeName = schemeName;
    }

    public MfSchemeType getMfSchemeType() {
        return mfSchemeType;
    }

    public void setMfSchemeType(final MfSchemeType mfSchemeType) {
        this.mfSchemeType = mfSchemeType;
    }

    public Set<MFSchemeNav> getMFschemeNavs() {
        return mFschemeNavs;
    }

    public void setMFschemeNavs(final Set<MFSchemeNav> mFschemeNavs) {
        this.mFschemeNavs = mFschemeNavs;
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
