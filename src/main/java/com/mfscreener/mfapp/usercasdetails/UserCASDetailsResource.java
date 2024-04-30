package com.mfscreener.mfapp.usercasdetails;

import com.mfscreener.mfapp.userinfo.UserInfo;
import com.mfscreener.mfapp.userinfo.UserInfoRepository;
import com.mfscreener.mfapp.util.CustomCollectors;
import com.mfscreener.mfapp.util.ReferencedException;
import com.mfscreener.mfapp.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/userCASDetailss", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserCASDetailsResource {

    private final UserCASDetailsService userCASDetailsService;
    private final UserInfoRepository userInfoRepository;

    public UserCASDetailsResource(final UserCASDetailsService userCASDetailsService,
            final UserInfoRepository userInfoRepository) {
        this.userCASDetailsService = userCASDetailsService;
        this.userInfoRepository = userInfoRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserCASDetailsDTO>> getAllUserCASDetailss() {
        return ResponseEntity.ok(userCASDetailsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserCASDetailsDTO> getUserCASDetails(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(userCASDetailsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createUserCASDetails(
            @RequestBody @Valid final UserCASDetailsDTO userCASDetailsDTO) {
        final Long createdId = userCASDetailsService.create(userCASDetailsDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserCASDetails(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final UserCASDetailsDTO userCASDetailsDTO) {
        userCASDetailsService.update(id, userCASDetailsDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUserCASDetails(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = userCASDetailsService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        userCASDetailsService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/userInfoValues")
    public ResponseEntity<Map<Long, Long>> getUserInfoValues() {
        return ResponseEntity.ok(userInfoRepository.findAll(Sort.by("id"))
                .stream()
                .collect(CustomCollectors.toSortedMap(UserInfo::getId, UserInfo::getId)));
    }

}
