package com.mfscreener.mfapp.mfscheme;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schemes")
public class MfSchemeController {

    private final MfSchemeService mfSchemeService;

    public MfSchemeController(MfSchemeService mfSchemeService) {
        this.mfSchemeService = mfSchemeService;
    }

    @GetMapping
    List<MfSchemeDTO> loadAll() {
        return mfSchemeService.loadAll();
    }

    @GetMapping("/{schemeCode}")
    MfSchemeDTO loadBySchemeCode(@PathVariable Long schemeCode) {
        return mfSchemeService.loadBySchemeCode(schemeCode);
    }
}
