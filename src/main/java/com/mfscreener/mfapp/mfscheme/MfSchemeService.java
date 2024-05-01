package com.mfscreener.mfapp.mfscheme;

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

@Service
@Transactional(readOnly = true)
public class MfSchemeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MfSchemeService.class);

    private final MfSchemeRepository mfSchemeRepository;
    private final ResourceLoader resourceLoader;
    private final MfSchemeDtoToEntityMapper mfSchemeDtoToEntityMapper;

    public MfSchemeService(
            MfSchemeRepository mfSchemeRepository,
            ResourceLoader resourceLoader,
            MfSchemeDtoToEntityMapper mfSchemeDtoToEntityMapper) {
        this.mfSchemeRepository = mfSchemeRepository;
        this.resourceLoader = resourceLoader;
        this.mfSchemeDtoToEntityMapper = mfSchemeDtoToEntityMapper;
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

    @Transactional
    public void loadHistoricalDataForClosedOrMergedSchemes() {
        Resource resource = resourceLoader.getResource("classpath:/nav/31Jan2018Navdatadump.csv");
        try {
            Path path = resource.getFile().toPath();
            List<String> lines = Files.lines(path).parallel().toList();
            List<MfScheme> mfSchemeEntities = lines.stream()
                    .skip(1)
                    .map(csvRow -> {
                        // Split the row , format nav	nav_date	scheme_id	fund_house	scheme_name	pay_out	type	category
                        //	sub_category
                        String[] fields = csvRow.split(",");

                        // Trim and remove quotes
                        for (int i = 0; i < fields.length; i++) {
                            fields[i] = fields[i].strip().replaceAll("^\"+|\"+$", "");
                        }
                        String schemeType;
                        if (fields[8].equals("NULL")) {
                            schemeType = fields[6].strip() + "(" + fields[7].strip() + ")";
                        } else {
                            schemeType = fields[6].strip() + "(" + fields[7].strip() + " - " + fields[8].strip() + ")";
                        }
                        return new MfSchemeDTO(
                                fields[3],
                                Long.valueOf(fields[2]),
                                fields[5],
                                fields[4],
                                fields[0],
                                fields[1],
                                schemeType);
                    })
                    .map(mfSchemeDtoToEntityMapper::mapMFSchemeDTOToMFSchemeEntity)
                    .toList();
            List<MfScheme> persistedEntities = mfSchemeRepository.saveAll(mfSchemeEntities);
            LOGGER.info("Persisted : {} rows", persistedEntities.size());
        } catch (IOException e) {
            LOGGER.error("Error reading NAV data file: {}", e.getMessage());
            throw new DataProcessingException("Error processing NAV data file");
        }
    }
}
