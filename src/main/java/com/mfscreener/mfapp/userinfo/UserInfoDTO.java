package com.mfscreener.mfapp.userinfo;

import jakarta.validation.constraints.Size;


public class UserInfoDTO {

    private Long id;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String mobile;

    @Size(max = 255)
    private String address;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

}
