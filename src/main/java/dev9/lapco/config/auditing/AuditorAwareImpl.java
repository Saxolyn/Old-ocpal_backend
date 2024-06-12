package dev9.lapco.config.auditing;

import dev9.lapco.config.security.UserDetailsImpl;
import dev9.lapco.constant.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@RequiredArgsConstructor
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        try{
            return Optional.of(((UserDetailsImpl) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal())
                    .getAdUsername());
                     }catch (Exception e){
            return Optional.of(ERole.SUPER_ADMIN.toString());
        }

    }
}
