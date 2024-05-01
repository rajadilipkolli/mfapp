package com.mfscreener.mfapp.mfschemenav;

import com.mfscreener.mfapp.mfscheme.MfSchemeRepository;
import com.mfscreener.mfapp.util.AppConstants;
import com.mfscreener.mfapp.web.exception.FileNotFoundException;
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

@Service
@Transactional(readOnly = true)
public class MfSchemeNavService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MfSchemeNavService.class);

    private final MfSchemeNavRepository mfSchemeNavRepository;
    private final MfSchemeRepository mfSchemeRepository;
    private final ResourceLoader resourceLoader;

    public MfSchemeNavService(
            MfSchemeNavRepository mfSchemeNavRepository,
            MfSchemeRepository mfSchemeRepository,
            ResourceLoader resourceLoader) {
        this.mfSchemeNavRepository = mfSchemeNavRepository;
        this.mfSchemeRepository = mfSchemeRepository;
        this.resourceLoader = resourceLoader;
    }

    public boolean navLoadedFor31Jan2018ForExistingSchemes() {
        return mfSchemeNavRepository.countByNavDate(AppConstants.GRAND_FATHERED_DATE) >= 5908;
    }

    @Transactional
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
            List<MfSchemeNav> persistedEntities = mfSchemeNavRepository.saveAll(mfSchemeNavEntities);
            LOGGER.info("Persisted : {} rows", persistedEntities.size());
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    public boolean navLoadedForClosedOrMergedSchemes() {
        return mfSchemeNavRepository.countByNavDate(AppConstants.GRAND_FATHERED_DATE) >= 9000;
    }
}
