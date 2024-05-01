package com.mfscreener.mfapp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public final class AppConstants {

    public static final String PROFILE_TEST = "test";
    public static final String AMFI_WEBSITE_LINK = "https://www.amfiindia.com/spages/NAVAll.txt";
    public static final String NAV_SEPARATOR = ";";
    public static final DateTimeFormatter FLEXIBLE_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[yyyy-MM-dd]") // ISO_LOCAL_DATE
            .appendPattern("[dd-MMM-yyyy]") // Custom format
            .parseDefaulting(ChronoField.YEAR_OF_ERA, LocalDate.now().getYear()) // Default year to current year
            .toFormatter(Locale.ENGLISH); // Ensure English locale for month names
}
