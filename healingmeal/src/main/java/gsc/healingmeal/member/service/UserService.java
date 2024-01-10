package gsc.healingmeal.member.service;

import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.member.dto.PrincipalDetail;
import gsc.healingmeal.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not Found User"));
        return new PrincipalDetail(user);
    }

    public Long userID(String authenticatedPrincipal){
        User user = userRepository.findByLoginId(authenticatedPrincipal).orElseThrow(()->new RuntimeException("Unbelieved error. principal is not found."));
        return user.getId();
    }
}