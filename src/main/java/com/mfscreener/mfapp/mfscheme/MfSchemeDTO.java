package com.mfscreener.mfapp.mfscheme;

import java.io.Serializable;

public record MfSchemeDTO(
        String amc, Long schemeCode, String payout, String schemeName, String nav, String date, String schemeType)
        implements Serializable {

    public MfSchemeDTO withNavAndDateAndSchemeType(String schemeType, String navValue, String navDate) {
        return new MfSchemeDTO(amc(), schemeCode(), payout(), schemeName(), navValue, navDate, schemeType);
    }
}
