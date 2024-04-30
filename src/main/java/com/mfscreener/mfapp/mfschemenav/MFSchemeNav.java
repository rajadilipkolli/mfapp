package com.mfscreener.mfapp.mfschemenav;

import com.mfscreener.mfapp.mfscheme.MfScheme;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(
        name = "mf_scheme_navs",
        uniqueConstraints = {
            @UniqueConstraint(
                    name = "uc_mf_scheme_nav",
                    columnNames = {"nav", "nav_date", "mf_scheme_id"})
        })
@EntityListeners(AuditingEntityListener.class)
public class MFSchemeNav {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private Double nav;

    @Column(nullable = false)
    private LocalDate navDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mf_scheme_id")
    private MfScheme mfScheme;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Long getId() {
        return id;
    }

    public MFSchemeNav setId(Long id) {
        this.id = id;
        return this;
    }

    public Double getNav() {
        return nav;
    }

    public MFSchemeNav setNav(Double nav) {
        this.nav = nav;
        return this;
    }

    public LocalDate getNavDate() {
        return navDate;
    }

    public MFSchemeNav setNavDate(LocalDate navDate) {
        this.navDate = navDate;
        return this;
    }

    public MfScheme getMfScheme() {
        return mfScheme;
    }

    public MFSchemeNav setMfScheme(MfScheme mfScheme) {
        this.mfScheme = mfScheme;
        return this;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public MFSchemeNav setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public MFSchemeNav setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy hp
                ? hp.getHibernateLazyInitializer().getPersistentClass()
                : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy hp
                ? hp.getHibernateLazyInitializer().getPersistentClass()
                : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MFSchemeNav that = (MFSchemeNav) o;
        return Objects.equals(getNav(), that.getNav())
                && Objects.equals(
                        getMfScheme().getSchemeId(), that.getMfScheme().getSchemeId())
                && Objects.deepEquals(getNavDate(), that.getNavDate());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy hp
                ? hp.getHibernateLazyInitializer().getPersistentClass().hashCode()
                : getClass().hashCode();
    }
}
