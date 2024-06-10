package dev9.lapco.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev9.lapco.constant.Message;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.request.ChangPasswordRequest;
import dev9.lapco.request.LoginRequest;
import dev9.lapco.request.RestorePasswordRequest;
import dev9.lapco.response.BaseResponse;
import dev9.lapco.response.LoginResponse;
import dev9.lapco.service.AccountService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController implements Message, StatusCode {

    private final AccountService accountService;

    @PostMapping("/login")
    private LoginResponse login(@RequestBody LoginRequest account) {
        return accountService.login(account);
    }

    @PutMapping("/restore-password")
    private BaseResponse restorePassword(RestorePasswordRequest request) {
        return accountService.restorePassword(request);
    }

    @PutMapping("/change-password")
//    @PreAuthorize("hasAuthority(authority.UPDATE_PASSWORD_AUTH_02_LEVEL)")
    private BaseResponse changePassword(@RequestBody ChangPasswordRequest request) {
        return accountService.changePassword(request);
    }


}
