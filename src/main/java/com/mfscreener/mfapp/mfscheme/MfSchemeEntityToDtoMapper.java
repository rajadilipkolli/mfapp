package com.mfscreener.mfapp.mfscheme;

import com.mfscreener.mfapp.mfschemetype.MfSchemeType;
import java.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class MfSchemeEntityToDtoMapper {

    public MfSchemeDTO convertToDTO(MfScheme mfScheme) {
        if (mfScheme == null) {
            return null;
        }

        Long schemeCode = mfScheme.getSchemeId();
        String payout = mfScheme.getIsin();
        String amc = mfScheme.getFundHouse();
        String schemeName = mfScheme.getSchemeName();
        String date = null;
        String nav = null;
        if (!CollectionUtils.isEmpty(mfScheme.getMfSchemeNavs())) {
            LocalDate localDate = mfScheme.getMfSchemeNavs().getFirst().getNavDate();
            nav = String.valueOf(mfScheme.getMfSchemeNavs().getFirst().getNav());
            if (null != localDate) {
                date = localDate.toString();
            }
        }
        MfSchemeType mfSchemeType = mfScheme.getMfSchemeType();
        String subCategory = mfSchemeType.getSubCategory();
        String category = mfSchemeType.getCategory();
        String categoryAndSubCategory;
        if (StringUtils.hasText(subCategory)) {
            categoryAndSubCategory = category + " - " + subCategory;
        } else {
            categoryAndSubCategory = category;
        }
        String schemeType = mfSchemeType.getType() + "(" + categoryAndSubCategory + ")";

        return new MfSchemeDTO(amc, schemeCode, payout, schemeName, nav, date, schemeType);
    }
}
