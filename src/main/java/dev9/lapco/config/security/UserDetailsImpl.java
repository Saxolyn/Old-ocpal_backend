package dev9.lapco.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev9.lapco.constant.ERole;
import dev9.lapco.entity.AccountEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String phoneNumber;

    private String adUsername;

    @JsonIgnore
    private String password;

    private ERole role;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String id, String phoneNumber, String adUsername, String password, ERole roles,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.adUsername = adUsername;
        this.password = password;
        this.role = roles;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(AccountEntity account, ERole roles, List<SimpleGrantedAuthority> authorities) {
                return new UserDetailsImpl(
                account.getId(),
                account.getUsername(),
                account.getPhoneNumber(),
                account.getPassword(),
                        roles,
                        authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }



}
