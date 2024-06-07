package dev9.lapco.service;

import dev9.lapco.request.ChangPasswordRequest;
import dev9.lapco.request.LoginRequest;
import dev9.lapco.request.RestorePasswordRequest;
import dev9.lapco.response.BaseResponse;
import dev9.lapco.response.LoginResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

public interface AccountService {

    LoginResponse login(LoginRequest account);

    BaseResponse restorePassword(RestorePasswordRequest request);

    BaseResponse changePassword(ChangPasswordRequest request, HttpServletRequest httpRequest);
}
