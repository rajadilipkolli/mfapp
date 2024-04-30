package com.mfscreener.mfapp.usercasdetails;

import com.mfscreener.mfapp.userinfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserCASDetailsRepository extends JpaRepository<UserCASDetails, Long> {

    UserCASDetails findFirstByUserInfo(UserInfo userInfo);

    boolean existsByUserInfoId(Long id);

}
