package gsc.healingmeal.member.service;

import gsc.healingmeal.member.domain.User;
import gsc.healingmeal.member.dto.PwdChangeDto;
import gsc.healingmeal.member.execption.InvalidPasswordException;
import gsc.healingmeal.member.execption.MismatchException;
import gsc.healingmeal.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class UserInfoModify {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //비밀번호 변경
    public void changePwd(PwdChangeDto pwdChangeDto, String loginId){
        if (validatePwd(pwdChangeDto.getChangePwd())){
            Optional<User> optionalUser = userRepository.findByLoginId(loginId);
            User user = optionalUser.get();
            if(passwordEncoder.matches(pwdChangeDto.getNowPwd(),user.getPassword())){
                user.setPassword(passwordEncoder.encode(pwdChangeDto.getChangePwd()));
                userRepository.save(user);
            } else{
                throw new MismatchException("the password is mismatch.");
            }
        } else {
            throw new InvalidPasswordException("the password is invalid. please, follow the rule.");
        }
    }
    //-비밀번호 변경 전 유효성 검사
    boolean validatePwd(String changePwd){
        String REGEX = "^[0-9a-zA-Z]{6,8}$";
        return Pattern.matches(REGEX, changePwd);
    }

    //임시 비밀번호 발행
    protected String generateTemPwd(int length){

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        return IntStream.range(0, length)
                .map(i -> random.nextInt(chars.length()))
                .mapToObj(randomIndex -> String.valueOf(chars.charAt(randomIndex)))
                .collect(Collectors.joining());
    }
}
