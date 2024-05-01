package com.mfscreener.mfapp.mfschemetype;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

interface MfSchemeTypeRepository extends JpaRepository<MfSchemeType, Long> {

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "mfSchemeType", unless = "#result == null")
    MfSchemeType findByTypeAndCategoryAndSubCategory(String type, String category, String subCategory);
}
