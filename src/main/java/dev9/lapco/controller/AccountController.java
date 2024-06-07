package dev9.lapco.controller;

import dev9.lapco.config.jwt.JwtUtils;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.dto.AccountDTO;
import dev9.lapco.response.LoginResponse;
import dev9.lapco.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController implements dev9.lapco.constant.Message, StatusCode {

    private final AccountService accountService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;


    @PostMapping("/login")
    private LoginResponse login(@RequestBody AccountDTO account) {


//        LoginResponse loginResponse = accountService.login(account);

//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
//                .body(new LoginResponse(userDetails.getId(),
//                        userDetails.getUsername(),
//                        userDetails.getEmail(),
//                        roles));
        return accountService.login(account);

//        return accountService.login(account.getUsername(), account.getPassword());
    }

//    @PostMapping("/Administrator")
//    private LoginResponse loginByAdmin(@RequestBody Account account) {
//        return accountService.loginByAdmin(account.getUsername(), account.getPassword());
//    }

//    @PostMapping("/validate")
//    private LoginResponse validate(@RequestBody Account account) {
//        return null;
//    }



}
