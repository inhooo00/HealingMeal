package gsc.healingmeal.member.controller;

import gsc.healingmeal.member.dto.JoinRequestDto;
import gsc.healingmeal.member.service.UserJoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserJoinController {

    private final UserJoinService userJoinService;
    public UserJoinController(UserJoinService userJoinService){ this.userJoinService = userJoinService;}
    @PostMapping("/user/join")
    public ResponseEntity<String> userJoin(@RequestBody JoinRequestDto joinRequestDto){
        userJoinService.join(joinRequestDto);
        return new ResponseEntity<>("Membership registration completed!" , HttpStatus.OK);
    }
}