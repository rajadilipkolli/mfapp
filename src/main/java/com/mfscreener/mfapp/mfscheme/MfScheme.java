package com.mfscreener.mfapp.mfscheme;

import com.mfscreener.mfapp.mfschemenav.MfSchemeNav;
import com.mfscreener.mfapp.mfschemetype.MfSchemeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mf_schemes")
@EntityListeners(AuditingEntityListener.class)
public class MfScheme {

    @Id
    @Column(nullable = false, updatable = false)
    private Long schemeId;

    @Column(length = 15)
    private String isin;

    @Column(length = 100)
    private String fundHouse;

    @Column(nullable = false)
    private String schemeName;

    @Version
    private Short version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_scheme_type_id")
    private MfSchemeType mfSchemeType;

    @OneToMany(mappedBy = "mfScheme")
    private List<MfSchemeNav> mfSchemeNavs = new ArrayList<>();

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getSchemeId() {
        return schemeId;
    }

    public MfScheme setSchemeId(Long schemeId) {
        this.schemeId = schemeId;
        return this;
    }

    public String getIsin() {
        return isin;
    }

    public MfScheme setIsin(String isin) {
        this.isin = isin;
        return this;
    }

    public String getFundHouse() {
        return fundHouse;
    }

    public MfScheme setFundHouse(String fundHouse) {
        this.fundHouse = fundHouse;
        return this;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public MfScheme setSchemeName(String schemeName) {
        this.schemeName = schemeName;
        return this;
    }

    public Short getVersion() {
        return version;
    }

    public MfScheme setVersion(Short version) {
        this.version = version;
        return this;
    }

    public MfSchemeType getMfSchemeType() {
        return mfSchemeType;
    }

    public MfScheme setMfSchemeType(MfSchemeType mfSchemeType) {
        this.mfSchemeType = mfSchemeType;
        return this;
    }

    public List<MfSchemeNav> getMfSchemeNavs() {
        return mfSchemeNavs;
    }

    public MfScheme setMfSchemeNavs(List<MfSchemeNav> mfSchemeNavs) {
        this.mfSchemeNavs = mfSchemeNavs;
        return this;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public MfScheme setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public MfScheme setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    public MfScheme addSchemeNav(MfSchemeNav mfSchemeNav) {
        mfSchemeNavs.add(mfSchemeNav);
        mfSchemeNav.setMfScheme(this);
        return this;
    }
}
