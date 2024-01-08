package gsc.healingmeal.member.service;

import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.member.dto.UserSearchDto;
import gsc.healingmeal.member.execption.InvalidEmailAddressException;
import gsc.healingmeal.member.execption.InvalidUserException;
import gsc.healingmeal.member.execption.InvalidUserNameException;
import gsc.healingmeal.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInfoModify userInfoModify;
    /*
    //이름, 이메일로 아이디 찾기
     */
    public UserSearchDto searchId(String name, String email) {
        if (userRepository.existsByName(name)){
            if (userRepository.existsByEmail(email)){
                User user = userRepository.findByEmailAndName(email, name).orElseThrow(()-> new InvalidUserException("user not found in the user list table."));
                return UserSearchDto.builder()
                        .loginId(user.getLoginId()).build();
            } else{
                throw new InvalidEmailAddressException("email not found");
            }
        } else {
            throw new InvalidUserNameException("user name not found");

        }
    }

    //이름, 이메일, 아이디로 비밀번호 찾기
    /*
    임시 비밀번호를 발급해준 뒤 user의 password를 임시비밀번호로 변경.
    향후 user가 자신의 password를 원하는대로 변경하도록 함.
     */
    public String searchPassword(String name, String email, String loginId){
        if (!userRepository.existsByEmail(email)) {
            throw new InvalidEmailAddressException("User email not Found");
        }
        if (!userRepository.existsByName(name)){
            throw new InvalidUserNameException("User name not Found");
        }
        if (!userRepository.existsByLoginId(loginId)){
            throw new InvalidUserException("Not Found ID");
        }
        String temPwd = userInfoModify.generateTemPwd(8);
        String encoded = passwordEncoder.encode(temPwd);
        User user = userRepository.findByEmail(email);
        user.setPassword(encoded);
        userRepository.save(user);
        return temPwd;
    }
}
