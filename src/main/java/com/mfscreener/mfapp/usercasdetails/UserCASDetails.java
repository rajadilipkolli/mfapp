package com.mfscreener.mfapp.usercasdetails;

import com.mfscreener.mfapp.userfoliodetails.UserFolioDetails;
import com.mfscreener.mfapp.userinfo.UserInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "UserCASDetailses")
@EntityListeners(AuditingEntityListener.class)
public class UserCASDetails {

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
    @Enumerated(EnumType.STRING)
    private CASType casType;

    @Column
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_info_id", nullable = false, unique = true)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "userCASDetails")
    private Set<UserFolioDetails> userFolioDetails;

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

    public CASType getCasType() {
        return casType;
    }

    public void setCasType(final CASType casType) {
        this.casType = casType;
    }

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(final FileType fileType) {
        this.fileType = fileType;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(final UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<UserFolioDetails> getUserFolioDetails() {
        return userFolioDetails;
    }

    public void setUserFolioDetails(final Set<UserFolioDetails> userFolioDetails) {
        this.userFolioDetails = userFolioDetails;
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
