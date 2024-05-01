package com.mfscreener.mfapp.mfschemenav;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

interface MfSchemeNavRepository extends JpaRepository<MfSchemeNav, Long> {

    @Transactional(readOnly = true)
    long countByNavDate(LocalDate navDate);
}
