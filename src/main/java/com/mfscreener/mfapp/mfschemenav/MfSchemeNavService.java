package com.mfscreener.mfapp.mfschemenav;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mfscreener.mfapp.mfscheme.MfSchemeRepository;
import com.mfscreener.mfapp.util.AppConstants;

@Service
@Transactional(readOnly = true)
public class MfSchemeNavService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MfSchemeNavService.class);

    private final MfSchemeNavRepository mfSchemeNavRepository;
    private final MfSchemeRepository mfSchemeRepository;
    private final ResourceLoader resourceLoader;

   public MfSchemeNavService(MfSchemeNavRepository mfSchemeNavRepository, MfSchemeRepository mfSchemeRepository,
        ResourceLoader resourceLoader) {
    this.mfSchemeNavRepository = mfSchemeNavRepository;
    this.mfSchemeRepository = mfSchemeRepository;
    this.resourceLoader = resourceLoader;
   }

   
   public boolean navLoadedFor31Jan2018ForExistingSchemes() {
    return mfSchemeNavRepository.countByNavDate(AppConstants.GRAND_FATHERED_DATE) >= 5908;
}

 public void loadHistoricalNavOn31Jan2018ForExistingSchemes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loadHistoricalNavOn31Jan2018ForExistingSchemes'");
    }

}
