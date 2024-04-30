package com.mfscreener.mfapp.usercasdetails;

import jakarta.validation.constraints.NotNull;


public class UserCASDetailsDTO {

    private Long id;

    private CASType casType;

    private FileType fileType;

    @NotNull
    @UserCASDetailsUserInfoUnique
    private Long userInfo;

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

    public Long getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(final Long userInfo) {
        this.userInfo = userInfo;
    }

}
