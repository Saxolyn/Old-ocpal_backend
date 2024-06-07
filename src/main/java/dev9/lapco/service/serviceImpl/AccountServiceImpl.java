package dev9.lapco.service.serviceImpl;

import dev9.lapco.config.jwt.JwtUtils;
import dev9.lapco.config.security.UserDetailsImpl;
import dev9.lapco.constant.ERole;
import dev9.lapco.constant.StatusCode;
import dev9.lapco.dto.AccountDTO;
import dev9.lapco.entity.AccountEntity;
import dev9.lapco.repository.AccountRepository;
import dev9.lapco.response.LoginResponse;
import dev9.lapco.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService, StatusCode {

    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Override
    public LoginResponse login(AccountDTO account) {
        try{
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(account.getPhoneNumber(), account.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setPhoneNumber(account.getPhoneNumber());
        accountEntity.setRole(ERole.valueOf(roles.get(0)));
        return LoginResponse.builder().status(OK).errorCode(false).account(accountEntity).token(jwtToken).build();
        } catch (Exception e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", new Throwable(e.getMessage()));
        }
    }

    @Override
    public LoginResponse loginByAdmin(String username, String password) {
        return null;
    }
}
