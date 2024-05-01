package com.mfscreener.mfapp.mfscheme;

import com.mfscreener.mfapp.mfschemenav.MFSchemeNav;
import com.mfscreener.mfapp.mfschemetype.MfSchemeType;
import com.mfscreener.mfapp.mfschemetype.MfSchemeTypeService;
import com.mfscreener.mfapp.util.AppConstants;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MfSchemeDtoToEntityMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(MfSchemeDtoToEntityMapper.class);

    private final MfSchemeTypeService mfSchemeTypeService;

    public MfSchemeDtoToEntityMapper(MfSchemeTypeService mfSchemeTypeService) {
        this.mfSchemeTypeService = mfSchemeTypeService;
    }

    public MfScheme mapMFSchemeDTOToMFSchemeEntity(MfSchemeDTO scheme) {
        if (scheme == null) {
            return null;
        }

        MfScheme mfScheme = new MfScheme();

        mfScheme.setFundHouse(scheme.amc());
        mfScheme.setIsin(scheme.payout());
        mfScheme.setSchemeId(scheme.schemeCode());
        mfScheme.setSchemeName(scheme.schemeName());

        updateMFScheme(scheme, mfScheme);

        return mfScheme;
    }

    private void updateMFScheme(MfSchemeDTO scheme, MfScheme mfScheme) {
        MFSchemeNav mfSchemenavEntity = new MFSchemeNav();
        mfSchemenavEntity.setNav("N.A.".equals(scheme.nav()) ? 0D : Double.parseDouble(scheme.nav()));
        // Use the flexible formatter to parse the date
        LocalDate parsedDate = LocalDate.parse(scheme.date(), AppConstants.FLEXIBLE_DATE_FORMATTER);
        mfSchemenavEntity.setNavDate(parsedDate);
        mfScheme.addSchemeNav(mfSchemenavEntity);

        MfSchemeType mfSchemeTypeEntity = null;
        String schemeType = scheme.schemeType();
        String type = schemeType.substring(0, schemeType.indexOf('(')).strip();
        if (schemeType.contains("(") && schemeType.contains("-") && schemeType.contains(")")) {
            String category = schemeType
                    .substring(schemeType.indexOf('(') + 1, schemeType.indexOf('-'))
                    .strip();
            String subCategory = schemeType
                    .substring(schemeType.indexOf('-') + 1, schemeType.length() - 1)
                    .strip();
            mfSchemeTypeEntity = mfSchemeTypeService.findOrCreateMFSchemeTypeEntity(type, category, subCategory);
        } else {
            if (!schemeType.contains("-")) {
                String category = schemeType
                        .substring(schemeType.indexOf('(') + 1, schemeType.length() - 1)
                        .strip();
                mfSchemeTypeEntity = mfSchemeTypeService.findOrCreateMFSchemeTypeEntity(type, category, null);
            } else {
                LOGGER.error("Unable to parse schemeType :{}", schemeType);
            }
        }
        mfScheme.setMfSchemeType(mfSchemeTypeEntity);
    }
}
