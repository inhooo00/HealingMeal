package gsc.healingmeal.member.controller;

import gsc.healingmeal.member.repository.UserRepository;
import gsc.healingmeal.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /*
    로그인 테스트

    Postman으로 테스트 시 raw가 아닌 form-data로 해야 한다.
     */
    //login authorization test
    @GetMapping("/success")
    public ResponseEntity notSesstion(@AuthenticationPrincipal Principal principal) {
        return new ResponseEntity("Login Or Authorization success", HttpStatus.OK);
    }
    //securityconfig에 로그인폼 관련 설정을 해놨기에 로그인 API작성이 불필요하나 테스트용으로 작성
    @GetMapping("/")
    public ResponseEntity<String> success(){
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}