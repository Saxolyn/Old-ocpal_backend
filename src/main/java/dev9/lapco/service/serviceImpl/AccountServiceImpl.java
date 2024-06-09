package dev9.lapco.service.serviceImpl;

import dev9.lapco.commonUtil.ValidateUtil;
import dev9.lapco.config.jwt.JwtUtils;
import dev9.lapco.config.security.UserDetailsImpl;
import dev9.lapco.constant.Message;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.entity.AccountEntity;
import dev9.lapco.repository.AccountRepository;
import dev9.lapco.request.ChangPasswordRequest;
import dev9.lapco.request.LoginRequest;
import dev9.lapco.request.RestorePasswordRequest;
import dev9.lapco.response.BaseResponse;
import dev9.lapco.response.LoginResponse;
import dev9.lapco.service.AccountService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, StatusCode, Message {

    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final PasswordEncoder passwordEncoder;

    @Value("${Application.defaultPassword}")
    private String defaultPassword;

    @Override
    public LoginResponse login(LoginRequest account) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(account.getPhoneNumber(), account.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            String jwtToken = jwtUtils.generateToken(userDetails);

            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setPhoneNumber(account.getPhoneNumber());
            accountEntity.setRole(userDetails.getRole());
            return LoginResponse.builder().status(SUCCESS).errorCode(false).account(accountEntity).token(jwtToken).build();
        } catch (Exception e) {
            throw new BadCredentialsException(ME0006, new Throwable(e.getMessage()));
        }
    }

    @Override
    public BaseResponse restorePassword(RestorePasswordRequest request) {
        Optional<AccountEntity> account = accountRepository.findAccount(request.getPhoneNumber());
        if (account.isEmpty()) {
            return BaseResponse.builder().status(NOT_FOUND).message(MI0002).build();
        }
        switch (account.get().getRole()) {
            case SUPER_ADMIN:
            case ADMIN:
                account.get().setPassword(passwordEncoder.encode(defaultPassword));
                accountRepository.save(account.get());
                return BaseResponse.builder().status(SUCCESS).message(MI0005).build();
            case TEACHER:
            case STUDENT:
                return BaseResponse.builder().status(SUCCESS).message(MI0004).build();
            default:
                return BaseResponse.builder().status(BAD_REQUEST).message(MI0004).build();
        }


    }

    @Override
    public BaseResponse changePassword(ChangPasswordRequest request, HttpServletRequest httpRequest) {
        Optional<AccountEntity> account = accountRepository.findAccount(jwtUtils.getPhoneNumberFromRequest(httpRequest));
        if(account.isEmpty()) {
            return BaseResponse.builder().status(NOT_FOUND).message(ME0004).build();
        }

        if(!ValidateUtil.isValidPassword(request.getOldPassword(),request.getNewPassword(), request.getConfirmPassword())){
            return BaseResponse.builder().status(BAD_REQUEST).message(MI0004).build();
        }
        account.get().setPassword(passwordEncoder.encode(request.getNewPassword()));
        accountRepository.save(account.get());
        return BaseResponse.builder().status(SUCCESS).message(MI0006).build();
    }
}
