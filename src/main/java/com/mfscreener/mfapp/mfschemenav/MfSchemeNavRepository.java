package com.mfscreener.mfapp.mfschemenav;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

interface MfSchemeNavRepository extends JpaRepository<MfSchemeNav, Long> {

    long countByNavDate(LocalDate navDate);
}
