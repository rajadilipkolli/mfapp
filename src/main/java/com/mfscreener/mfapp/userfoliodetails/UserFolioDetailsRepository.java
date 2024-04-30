package com.mfscreener.mfapp.userfoliodetails;

import com.mfscreener.mfapp.usercasdetails.UserCASDetails;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserFolioDetailsRepository extends JpaRepository<UserFolioDetails, Long> {

    UserFolioDetails findFirstByUserCASDetails(UserCASDetails userCASDetails);

}
