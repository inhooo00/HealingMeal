package gsc.healingmeal.member.service;

import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.member.domain.PrincipalDetail;
import gsc.healingmeal.member.execption.InvalidUserException;
import gsc.healingmeal.member.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;

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

    //유저 번호 반환
    public Long userID(String authenticatedPrincipal){
        User user = userRepository.findByLoginId(authenticatedPrincipal).orElseThrow(()->new RuntimeException("Unbelieved error. principal is not found."));
        return user.getId();
    }

    //로그인 상태 확인 메서드
    //로그인 상태면 로그인한 유저의 이름을 반환하고, 아니면 로그인 상태가 아님을 반환.
    public String loginConfirm(HttpServletRequest request) throws InvalidUserException {
        Principal user = request.getUserPrincipal();
        if (user != null){
            User loginUser = userRepository.findByLoginId(user.getName().toString()).orElseThrow(()-> new InvalidUserException("로그인 상태가 아닙니다."));
            return loginUser.getName();
        }
        throw new InvalidUserException("not login");
    }
}