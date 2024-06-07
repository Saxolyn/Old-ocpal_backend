package dev9.lapco.config.security;

import dev9.lapco.entity.AccountEntity;
import dev9.lapco.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findAccount(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + phoneNumber));
        return UserDetailsImpl.build(account);
    }

}