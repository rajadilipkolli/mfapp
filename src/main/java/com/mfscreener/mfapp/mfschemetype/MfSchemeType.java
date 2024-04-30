package com.mfscreener.mfapp.mfschemetype;

import com.mfscreener.mfapp.mfscheme.MfScheme;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "mf_scheme_types", uniqueConstraints = @UniqueConstraint(columnNames = {"type", "category"}))
@EntityListeners(AuditingEntityListener.class)
public class MfSchemeType {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "scheme_type_id_generator")
    @SequenceGenerator(name = "scheme_type_id_generator", sequenceName = "scheme_type_id_seq", allocationSize = 2)
    private Integer id;

    @Column
    private String type;

    @Column
    private String category;

    @Column
    private String subCategory;

    @OneToMany(mappedBy = "mfSchemeType")
    private Set<MfScheme> mfSchemes;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getId() {
        return id;
    }

    public MfSchemeType setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public MfSchemeType setType(String type) {
        this.type = type;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public MfSchemeType setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public MfSchemeType setSubCategory(String subCategory) {
        this.subCategory = subCategory;
        return this;
    }

    public Set<MfScheme> getMfSchemes() {
        return mfSchemes;
    }

    public MfSchemeType setMfSchemes(Set<MfScheme> mfSchemes) {
        this.mfSchemes = mfSchemes;
        return this;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public MfSchemeType setDateCreated(OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public MfSchemeType setLastUpdated(OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
        return this;
    }
}
