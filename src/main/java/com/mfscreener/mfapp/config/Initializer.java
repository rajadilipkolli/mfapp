package com.mfscreener.mfapp.config;

import com.mfscreener.mfapp.mfscheme.MfSchemeDTO;
import com.mfscreener.mfapp.mfscheme.MfScheme;
import com.mfscreener.mfapp.mfscheme.MfSchemeDtoToEntityMapper;
import com.mfscreener.mfapp.mfscheme.MfSchemeService;
import com.mfscreener.mfapp.mfschemenav.MfSchemeNavService;
import com.mfscreener.mfapp.util.AppConstants;
import com.mfscreener.mfapp.web.service.RestClientService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

@Component
public class Initializer implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Initializer.class);

    private final MfSchemeService mfSchemeService;
    private final MfSchemeDtoToEntityMapper mfSchemeDtoToEntityMapper;
    private final RestClientService restClientService;
    private final MfSchemeNavService mfSchemeNavService;

    public Initializer(
            MfSchemeService mfSchemeService,
            MfSchemeDtoToEntityMapper mfSchemeDtoToEntityMapper,
            RestClientService restClientService,
            MfSchemeNavService mfSchemeNavService) {
        this.mfSchemeService = mfSchemeService;
        this.mfSchemeDtoToEntityMapper = mfSchemeDtoToEntityMapper;
        this.restClientService = restClientService;
        this.mfSchemeNavService = mfSchemeNavService;
    }

    @Override
    public void run(String... args) throws IOException {
        long start = System.currentTimeMillis();
        LOGGER.info("Loading All Funds...");
        try {
            String allNAVs = restClientService.getForObject(AppConstants.AMFI_WEBSITE_LINK);
            Reader inputString = new StringReader(Objects.requireNonNull(allNAVs));
            List<MfSchemeDTO> chopArrayList = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(inputString)) {
                String lineValue = br.readLine();
                for (int i = 0; i < 2; ++i) {
                    lineValue = br.readLine();
                }
                String schemeType = lineValue;
                String amc = lineValue;
                while (lineValue != null) {
                    String[] tokenize = lineValue.split(AppConstants.NAV_SEPARATOR);
                    if (tokenize.length == 1) {
                        String tempVal = lineValue;
                        lineValue = br.readLine();
                        if (!StringUtils.hasText(lineValue)) {
                            lineValue = br.readLine();
                            tokenize = lineValue.split(AppConstants.NAV_SEPARATOR);
                            if (tokenize.length == 1) {
                                schemeType = tempVal;
                                amc = lineValue;
                            } else {
                                amc = tempVal;
                                handleMultipleTokenRow(tokenize, amc, schemeType, chopArrayList);
                            }
                        }
                    } else {
                        handleMultipleTokenRow(tokenize, amc, schemeType, chopArrayList);
                    }
                    lineValue = br.readLine();
                    if (!StringUtils.hasText(lineValue)) {
                        lineValue = br.readLine();
                    }
                }
            }
            LOGGER.info(
                    "All Funds parsed in {} milliseconds, total funds parsed :{}",
                    (System.currentTimeMillis() - start),
                    chopArrayList.size());

            if (mfSchemeService.count() != chopArrayList.size()) {
                StopWatch stopWatch = new StopWatch();
                stopWatch.start("saving fundNames");
                List<MfScheme> toBeInsertedList = new ArrayList<>();
                List<Long> schemeCodesList = mfSchemeService.getAllAvailableSchemeCodeFromDb();
                chopArrayList.removeIf(s -> schemeCodesList.contains(s.schemeCode()));
                chopArrayList.forEach(mfSchemeDTO -> {
                    MfScheme mfSchemeEntity = mfSchemeDtoToEntityMapper.mapMFSchemeDTOToMFSchemeEntity(mfSchemeDTO);
                    toBeInsertedList.add(mfSchemeEntity);
                });
                mfSchemeService.saveAllEntities(toBeInsertedList);
                stopWatch.stop();
                LOGGER.info("saved in db in : {} sec", stopWatch.getTotalTimeSeconds());
            }
        } catch (HttpClientErrorException | ResourceAccessException httpClientErrorException) {
            // eating as we can't do much, it should be set when available using Nightly job
            LOGGER.error("Unable to load data from :{}", AppConstants.AMFI_WEBSITE_LINK, httpClientErrorException);
        }
    }

    if (!mfSchemeNavService.navLoadedFor31Jan2018ForExistingSchemes()) {
        mfSchemeNavService.loadHistoricalNavOn31Jan2018ForExistingSchemes();
    }

    if (!mfSchemeNavService.navLoadedForClosedOrMergedSchemes()) {
        mfSchemeService.loadHistoricalDataForClosedOrMergedSchemes();
    }

    private void handleMultipleTokenRow(
            String[] tokenize, String amc, String schemeType, List<MfSchemeDTO> chopArrayList) {
        final String schemecode = tokenize[0];
        final String payout = tokenize[1];
        final String schemename = tokenize[3];
        final String nav = tokenize[4];
        final String date = tokenize[5];
        final MfSchemeDTO tempObj =
                new MfSchemeDTO(amc, Long.valueOf(schemecode), payout, schemename, nav, date, schemeType);
        chopArrayList.add(tempObj);
    }
}
