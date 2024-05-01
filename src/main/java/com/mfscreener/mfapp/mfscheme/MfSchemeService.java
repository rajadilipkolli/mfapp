package com.mfscreener.mfapp.mfscheme;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MfSchemeService {

    private final MfSchemeRepository mfSchemeRepository;

    public MfSchemeService(MfSchemeRepository mfSchemeRepository) {
        this.mfSchemeRepository = mfSchemeRepository;
    }

    public long count() {
        return mfSchemeRepository.count();
    }

    public List<Long> getAllAvailableSchemeCodeFromDb() {
        return mfSchemeRepository.findAllSchemeIds();
    }

    @Transactional
    public List<MfScheme> saveAllEntities(List<MfScheme> list) {
        return mfSchemeRepository.saveAll(list);
    }
}
