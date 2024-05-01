package com.mfscreener.mfapp.mfschemetype;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class MfSchemeTypeService {

    private final MfSchemeTypeRepository mfSchemeTypeRepository;

    public MfSchemeTypeService(MfSchemeTypeRepository mfSchemeTypeRepository) {
        this.mfSchemeTypeRepository = mfSchemeTypeRepository;
    }

    public MfSchemeType findOrCreateMfSchemeType(String type, String category, @Nullable String subCategory) {
        MfSchemeType byTypeAndCategoryAndSubCategory =
                mfSchemeTypeRepository.findByTypeAndCategoryAndSubCategory(type, category, subCategory);
        if (byTypeAndCategoryAndSubCategory == null) {
            MfSchemeType mfSchemeType = new MfSchemeType();
            mfSchemeType.setType(type);
            mfSchemeType.setCategory(category);
            mfSchemeType.setSubCategory(subCategory);
            byTypeAndCategoryAndSubCategory = mfSchemeTypeRepository.save(mfSchemeType);
        }
        return byTypeAndCategoryAndSubCategory;
    }
}
