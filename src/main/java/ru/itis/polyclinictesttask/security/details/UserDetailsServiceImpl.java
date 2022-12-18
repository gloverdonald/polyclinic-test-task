package ru.itis.polyclinictesttask.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.itis.polyclinictesttask.dto.response.AccountResponse;
import ru.itis.polyclinictesttask.exception.UserNotFoundException;
import ru.itis.polyclinictesttask.repository.AccountRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken token) throws UsernameNotFoundException {
        return loadUserDetails((AccountResponse) token.getPrincipal(), String.valueOf(token.getCredentials()));
    }

    private UserDetails loadUserDetails(AccountResponse userResponse, String token) {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_".concat(userResponse.getType()));
        List<SimpleGrantedAuthority> authorities = List.of(authority);
        return UserDetailsImpl.builder()
                .id(userResponse.getId())
                .username(userResponse.getEmail())
                .firstName(userResponse.getFirstName())
                .lastName(userResponse.getLastName())
                .account(accountRepository.findByEmail(userResponse.getEmail()).orElseThrow(UserNotFoundException::new))
                .authorities(authorities)
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .token(token)
                .build();
    }
}
