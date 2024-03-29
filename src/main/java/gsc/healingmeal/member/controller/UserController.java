package gsc.healingmeal.member.controller;

import gsc.healingmeal.member.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class  UserController {
    private final UserService userService;

    /*
    로그인 테스트
    Postman으로 테스트 시 raw가 아닌 form-data로 해야 한다.
     */
    //login authorization test
    @GetMapping("/success")
    public ResponseEntity notSesstion() {
        return new ResponseEntity("Login Or Authorization success", HttpStatus.OK);
    }
    //로그아웃 성공 시
    @GetMapping("/successlogout")
    public ResponseEntity logoutSesstion(HttpSession session) {
        session.invalidate();
        return new ResponseEntity(session.toString(), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<String> success (HttpServletRequest request, Authentication authentication){
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(1800);
        String user_id = userService.userID(authentication.getName()).toString();
        return new ResponseEntity<>(user_id, HttpStatus.OK);
    }

    @GetMapping("/user/confirm")
    public ResponseEntity<String> confirm (HttpServletRequest request){
        return new ResponseEntity<>(userService.loginConfirm(request), HttpStatus.OK);
    }

}