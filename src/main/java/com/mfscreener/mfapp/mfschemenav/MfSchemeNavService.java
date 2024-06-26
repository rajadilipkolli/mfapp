package com.mfscreener.mfapp.mfschemenav;

import com.mfscreener.mfapp.mfscheme.MfSchemeRepository;
import com.mfscreener.mfapp.util.AppConstants;
import com.mfscreener.mfapp.web.exception.DataProcessingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@Transactional(readOnly = true)
public class MfSchemeNavService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MfSchemeNavService.class);

    private final MfSchemeNavRepository mfSchemeNavRepository;
    private final MfSchemeRepository mfSchemeRepository;
    private final ResourceLoader resourceLoader;
    private final TransactionTemplate transactionTemplate;

    public MfSchemeNavService(
            MfSchemeNavRepository mfSchemeNavRepository,
            MfSchemeRepository mfSchemeRepository,
            ResourceLoader resourceLoader,
            TransactionTemplate transactionTemplate) {
        this.mfSchemeNavRepository = mfSchemeNavRepository;
        this.mfSchemeRepository = mfSchemeRepository;
        this.resourceLoader = resourceLoader;
        transactionTemplate.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
        this.transactionTemplate = transactionTemplate;
    }

    public boolean navLoadedFor31Jan2018ForExistingSchemes() {
        return mfSchemeNavRepository.countByNavDate(AppConstants.GRAND_FATHERED_DATE) >= 5905;
    }

    public boolean navLoadedForClosedOrMergedSchemes() {
        return mfSchemeNavRepository.countByNavDate(AppConstants.GRAND_FATHERED_DATE) >= 9500;
    }

    public void loadHistoricalNavOn31Jan2018ForExistingSchemes() {

        Resource resource = resourceLoader.getResource("classpath:/nav/31Jan2018Navdata.csv");
        try {
            Path path = resource.getFile().toPath();
            List<String> lines = Files.lines(path).parallel().toList();
            List<MfSchemeNav> mfSchemeNavEntities = lines.stream()
                    .skip(1)
                    .map(csvRow -> {
                        // Split the row
                        String[] fields = csvRow.split(",");

                        // Trim and remove quotes
                        for (int i = 0; i < fields.length; i++) {
                            fields[i] = fields[i].strip();
                        }
                        MfSchemeNav mfSchemeNav = new MfSchemeNav();
                        mfSchemeNav.setNav(Double.valueOf(fields[0]));
                        mfSchemeNav.setNavDate(AppConstants.GRAND_FATHERED_DATE);
                        mfSchemeNav.setMfScheme(
                                mfSchemeRepository.getReferenceById(Long.valueOf(fields[2].replace("\"\"", ""))));
                        return mfSchemeNav;
                    })
                    .toList();
            List<MfSchemeNav> persistedEntities =
                    transactionTemplate.execute(status -> mfSchemeNavRepository.saveAll(mfSchemeNavEntities));
            LOGGER.info("Persisted : {} rows", persistedEntities.size());
        } catch (IOException e) {
            LOGGER.error("Error reading NAV data file: {}", e.getMessage());
            throw new DataProcessingException("Error processing NAV data file");
        }
    }
}
