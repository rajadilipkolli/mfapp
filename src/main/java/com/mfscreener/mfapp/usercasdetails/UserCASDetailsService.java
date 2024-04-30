package com.mfscreener.mfapp.usercasdetails;

import com.mfscreener.mfapp.userfoliodetails.UserFolioDetails;
import com.mfscreener.mfapp.userfoliodetails.UserFolioDetailsRepository;
import com.mfscreener.mfapp.userinfo.UserInfo;
import com.mfscreener.mfapp.userinfo.UserInfoRepository;
import com.mfscreener.mfapp.util.NotFoundException;
import com.mfscreener.mfapp.util.ReferencedWarning;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserCASDetailsService {

    private final UserCASDetailsRepository userCASDetailsRepository;
    private final UserInfoRepository userInfoRepository;
    private final UserFolioDetailsRepository userFolioDetailsRepository;

    public UserCASDetailsService(final UserCASDetailsRepository userCASDetailsRepository,
            final UserInfoRepository userInfoRepository,
            final UserFolioDetailsRepository userFolioDetailsRepository) {
        this.userCASDetailsRepository = userCASDetailsRepository;
        this.userInfoRepository = userInfoRepository;
        this.userFolioDetailsRepository = userFolioDetailsRepository;
    }

    public List<UserCASDetailsDTO> findAll() {
        final List<UserCASDetails> userCASDetailses = userCASDetailsRepository.findAll(Sort.by("id"));
        return userCASDetailses.stream()
                .map(userCASDetails -> mapToDTO(userCASDetails, new UserCASDetailsDTO()))
                .toList();
    }

    public UserCASDetailsDTO get(final Long id) {
        return userCASDetailsRepository.findById(id)
                .map(userCASDetails -> mapToDTO(userCASDetails, new UserCASDetailsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserCASDetailsDTO userCASDetailsDTO) {
        final UserCASDetails userCASDetails = new UserCASDetails();
        mapToEntity(userCASDetailsDTO, userCASDetails);
        return userCASDetailsRepository.save(userCASDetails).getId();
    }

    public void update(final Long id, final UserCASDetailsDTO userCASDetailsDTO) {
        final UserCASDetails userCASDetails = userCASDetailsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userCASDetailsDTO, userCASDetails);
        userCASDetailsRepository.save(userCASDetails);
    }

    public void delete(final Long id) {
        userCASDetailsRepository.deleteById(id);
    }

    private UserCASDetailsDTO mapToDTO(final UserCASDetails userCASDetails,
            final UserCASDetailsDTO userCASDetailsDTO) {
        userCASDetailsDTO.setId(userCASDetails.getId());
        userCASDetailsDTO.setCasType(userCASDetails.getCasType());
        userCASDetailsDTO.setFileType(userCASDetails.getFileType());
        userCASDetailsDTO.setUserInfo(userCASDetails.getUserInfo() == null ? null : userCASDetails.getUserInfo().getId());
        return userCASDetailsDTO;
    }

    private UserCASDetails mapToEntity(final UserCASDetailsDTO userCASDetailsDTO,
            final UserCASDetails userCASDetails) {
        userCASDetails.setCasType(userCASDetailsDTO.getCasType());
        userCASDetails.setFileType(userCASDetailsDTO.getFileType());
        final UserInfo userInfo = userCASDetailsDTO.getUserInfo() == null ? null : userInfoRepository.findById(userCASDetailsDTO.getUserInfo())
                .orElseThrow(() -> new NotFoundException("userInfo not found"));
        userCASDetails.setUserInfo(userInfo);
        return userCASDetails;
    }

    public boolean userInfoExists(final Long id) {
        return userCASDetailsRepository.existsByUserInfoId(id);
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final UserCASDetails userCASDetails = userCASDetailsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final UserFolioDetails userCASDetailsUserFolioDetails = userFolioDetailsRepository.findFirstByUserCASDetails(userCASDetails);
        if (userCASDetailsUserFolioDetails != null) {
            referencedWarning.setKey("userCASDetails.userFolioDetails.userCASDetails.referenced");
            referencedWarning.addParam(userCASDetailsUserFolioDetails.getId());
            return referencedWarning;
        }
        return null;
    }

}
