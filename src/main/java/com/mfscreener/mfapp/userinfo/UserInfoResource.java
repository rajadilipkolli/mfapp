package com.mfscreener.mfapp.userinfo;

import com.mfscreener.mfapp.util.ReferencedException;
import com.mfscreener.mfapp.util.ReferencedWarning;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
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
@RequestMapping(value = "/api/userInfos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserInfoResource {

    private final UserInfoService userInfoService;

    public UserInfoResource(final UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping
    public ResponseEntity<List<UserInfoDTO>> getAllUserInfos() {
        return ResponseEntity.ok(userInfoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(userInfoService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createUserInfo(@RequestBody @Valid final UserInfoDTO userInfoDTO) {
        final Long createdId = userInfoService.create(userInfoDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateUserInfo(
            @PathVariable(name = "id") final Long id, @RequestBody @Valid final UserInfoDTO userInfoDTO) {
        userInfoService.update(id, userInfoDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteUserInfo(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = userInfoService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        userInfoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
