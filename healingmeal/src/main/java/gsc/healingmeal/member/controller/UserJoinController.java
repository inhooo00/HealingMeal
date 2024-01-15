package gsc.healingmeal.member.controller;

import gsc.healingmeal.member.dto.JoinIdRequestDto;
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

    @PostMapping("user/join/id")
    public ResponseEntity<String> userJoinId(@RequestBody JoinIdRequestDto joinRequestDto){
        boolean userId = userJoinService.isLoginIdDuplicate(joinRequestDto.getLoginId());
        if (!userId){
            return  new ResponseEntity<>("사용 가능한 아이디 입니다.", HttpStatus.OK);
        }
        return new ResponseEntity<>("아이디가 이미 사용 중입니다.", HttpStatus.NOT_FOUND);
    }


}
