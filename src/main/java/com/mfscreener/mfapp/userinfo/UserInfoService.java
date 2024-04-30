package com.mfscreener.mfapp.userinfo;

import com.mfscreener.mfapp.usercasdetails.UserCASDetails;
import com.mfscreener.mfapp.usercasdetails.UserCASDetailsRepository;
import com.mfscreener.mfapp.util.NotFoundException;
import com.mfscreener.mfapp.util.ReferencedWarning;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final UserCASDetailsRepository userCASDetailsRepository;

    public UserInfoService(final UserInfoRepository userInfoRepository,
            final UserCASDetailsRepository userCASDetailsRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userCASDetailsRepository = userCASDetailsRepository;
    }

    public List<UserInfoDTO> findAll() {
        final List<UserInfo> userInfoes = userInfoRepository.findAll(Sort.by("id"));
        return userInfoes.stream()
                .map(userInfo -> mapToDTO(userInfo, new UserInfoDTO()))
                .toList();
    }

    public UserInfoDTO get(final Long id) {
        return userInfoRepository.findById(id)
                .map(userInfo -> mapToDTO(userInfo, new UserInfoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final UserInfoDTO userInfoDTO) {
        final UserInfo userInfo = new UserInfo();
        mapToEntity(userInfoDTO, userInfo);
        return userInfoRepository.save(userInfo).getId();
    }

    public void update(final Long id, final UserInfoDTO userInfoDTO) {
        final UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(userInfoDTO, userInfo);
        userInfoRepository.save(userInfo);
    }

    public void delete(final Long id) {
        userInfoRepository.deleteById(id);
    }

    private UserInfoDTO mapToDTO(final UserInfo userInfo, final UserInfoDTO userInfoDTO) {
        userInfoDTO.setId(userInfo.getId());
        userInfoDTO.setEmail(userInfo.getEmail());
        userInfoDTO.setName(userInfo.getName());
        userInfoDTO.setMobile(userInfo.getMobile());
        userInfoDTO.setAddress(userInfo.getAddress());
        return userInfoDTO;
    }

    private UserInfo mapToEntity(final UserInfoDTO userInfoDTO, final UserInfo userInfo) {
        userInfo.setEmail(userInfoDTO.getEmail());
        userInfo.setName(userInfoDTO.getName());
        userInfo.setMobile(userInfoDTO.getMobile());
        userInfo.setAddress(userInfoDTO.getAddress());
        return userInfo;
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final UserCASDetails userInfoUserCASDetails = userCASDetailsRepository.findFirstByUserInfo(userInfo);
        if (userInfoUserCASDetails != null) {
            referencedWarning.setKey("userInfo.userCASDetails.userInfo.referenced");
            referencedWarning.addParam(userInfoUserCASDetails.getId());
            return referencedWarning;
        }
        return null;
    }

}
