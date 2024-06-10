package dev9.lapco.config.security;

import dev9.lapco.constant.Authority;
import dev9.lapco.constant.ERole;
import dev9.lapco.constant.Message;
import dev9.lapco.entity.AccountEntity;
import dev9.lapco.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService, Message {

    private final AccountRepository accountRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findAccount(phoneNumber).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + phoneNumber));
        List<SimpleGrantedAuthority> grantedAuthorities;
        ERole roles = account.getRole();
        switch (roles){
            case SUPER_ADMIN:
                List<String> superAdminAuthorityList = Authority.getSuperAdminAuthority();
                grantedAuthorities = superAdminAuthorityList.stream().map(SimpleGrantedAuthority::new).toList();
                break;
            case ADMIN:
                List<String> adminAuthorityList = Authority.getAdminAuthority();
                grantedAuthorities = adminAuthorityList.stream().map(SimpleGrantedAuthority::new).toList();
                break;
            case TEACHER:
                List<String> teacherAuthorityList = Authority.getTeacherAuthority();
                grantedAuthorities = teacherAuthorityList.stream().map(SimpleGrantedAuthority::new).toList();
                break;
            case STUDENT:
                List<String> studentAuthorityList = Authority.getStudentAuthority();
                grantedAuthorities = studentAuthorityList.stream().map(SimpleGrantedAuthority::new).toList();
                break;
            default:
                throw new UsernameNotFoundException(ME0003 + account.getUsername() + phoneNumber);
        }

        return UserDetailsImpl.build(account,roles, grantedAuthorities);
    }

}