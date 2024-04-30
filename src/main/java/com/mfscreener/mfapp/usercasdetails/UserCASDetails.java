package com.mfscreener.mfapp.usercasdetails;

import com.mfscreener.mfapp.userfoliodetails.UserFolioDetails;
import com.mfscreener.mfapp.userinfo.UserInfo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "user_cas_details")
@EntityListeners(AuditingEntityListener.class)
public class UserCASDetails {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private CASType casType;

    @Column
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @OneToOne(mappedBy = "userCASDetails", fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "userCASDetails")
    private List<UserFolioDetails> userFolioDetails;

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

    public List<UserFolioDetails> getUserFolioDetails() {
        return userFolioDetails;
    }

    public void setUserFolioDetails(final List<UserFolioDetails> userFolioDetails) {
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

    public UserCASDetails setUserInfo(UserInfo userInfo) {
        if (userInfo == null) {
            if (this.userInfo != null) {
                this.userInfo.setUserCASDetails(null);
            }
        } else {
            userInfo.setUserCASDetails(this);
        }
        this.userInfo = userInfo;
        return this;
    }

    public void addUserFolioDetails(UserFolioDetails userFolioDetails) {
        this.userFolioDetails.add(userFolioDetails);
        userFolioDetails.setUserCASDetails(this);
    }
}
