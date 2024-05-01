package com.mfscreener.mfapp.mfscheme;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MfSchemeRepository extends JpaRepository<MfScheme, Long> {

    @Query("select o.schemeId from MfScheme o")
    List<Long> findAllSchemeIds();
}
